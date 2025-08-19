Task 4: Server Deployment & Security
Objective

Deploy the Spring Boot invoice service on a Linux server (Ubuntu), configure Nginx reverse proxy with SSL, and ensure the app auto-restarts if it crashes.

1. Server Setup (Ubuntu on AWS EC2)
# Update packages
sudo apt update && sudo apt upgrade -y

# Install Java 17
sudo apt install openjdk-17-jdk -y

# Verify Java
java -version

# Install Maven (if not using wrapper)
sudo apt install maven -y

# Install Nginx
sudo apt install nginx -y

# Allow ports 22, 80, 443 in AWS Security Group

2. Build the Spring Boot JAR
# Navigate to project folder
cd ~/invoice-service/invoice-service

# Build the JAR using Maven wrapper
./mvnw clean package -U

# Verify JAR exists
ls -l target/
# Expected output: invoice-service-0.0.1-SNAPSHOT.jar

3. Generate Self-Signed SSL Certificate
# Create folder for SSL
sudo mkdir -p /etc/ssl/invoice-service
cd /etc/ssl/invoice-service

# Generate self-signed certificate
sudo openssl req -x509 -nodes -days 365 \
-newkey rsa:2048 \
-keyout invoice-service.key \
-out invoice-service.crt

# Fill required info (Common Name, Organization, City, Email)

4. Configure Nginx Reverse Proxy
# Create Nginx site config
sudo nano /etc/nginx/sites-available/invoice-service


Contents of /etc/nginx/sites-available/invoice-service:

server {
listen 80;
server_name <your-ec2-public-ip>;

    # Redirect all HTTP to HTTPS
    return 301 https://$host$request_uri;
}

server {
listen 443 ssl;
server_name <your-ec2-public-ip>;

    ssl_certificate /etc/ssl/invoice-service/invoice-service.crt;
    ssl_certificate_key /etc/ssl/invoice-service/invoice-service.key;

    location / {
        proxy_pass http://localhost:8080;
        proxy_set_header Host $host;
        proxy_set_header X-Real-IP $remote_addr;
        proxy_set_header X-Forwarded-For $proxy_add_x_forwarded_for;
    }
}

# Enable site
sudo ln -s /etc/nginx/sites-available/invoice-service /etc/nginx/sites-enabled/

# Test Nginx config
sudo nginx -t

# Restart Nginx
sudo systemctl restart nginx

5. Create systemd Service for Auto-Restart
   sudo nano /etc/systemd/system/invoice-service.service


Contents:

[Unit]
Description=Invoice Service Spring Boot App
After=network.target

[Service]
User=ubuntu
WorkingDirectory=/home/ubuntu/invoice-service/invoice-service
ExecStart=/usr/bin/java -jar /home/ubuntu/invoice-service/invoice-service/target/invoice-service-0.0.1-SNAPSHOT.jar
SuccessExitStatus=143
Restart=always
RestartSec=10

[Install]
WantedBy=multi-user.target

# Reload systemd and start service
sudo systemctl daemon-reload
sudo systemctl enable invoice-service
sudo systemctl start invoice-service

# Check status
sudo systemctl status invoice-service

# Tail logs if needed
journalctl -u invoice-service -f

6. Test Public API

Open browser or use curl:

http://56.228.16.202:8080/api/invoices?dealerId=1&vehicleId=1&customerName=Devashish


Browser may warn about self-signed certificate → click Proceed anyway.

API should return a downloadable PDF invoice.

7. Deliverables

Public API URL:

http://56.228.16.202:8080/api/invoices?dealerId=1&vehicleId=1&customerName=Devashish
http://56.228.16.202:8080/api/invoices?dealerId=2&vehicleId=2&customerName=Devashish


Setup Steps: Documented above.

App is managed by systemd, auto-restarts on crash, and served via Nginx with HTTPS.