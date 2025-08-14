# 🏨 Hotel Booking Application

A **full-stack hotel booking system** designed for privately-owned or large hotels, built with **Spring Boot**, **Next.js**, and **AWS**.  
This application provides a seamless booking experience with secure authentication, advanced filtering, online payments, and robust deployment on AWS.

---

## 🚀 Features
-Secure JWT-based Authentication & Role-based Authorization
-Spring Boot backend for RESTful API development
-Hibernate ORM with MySQL database for data persistence
-Robust exception handling and validation
-Asynchronous email notifications and Stripe payment integration
-Admin & customer roles with real-time booking management
-Building and packaging with Maven
-Scalable AWS infrastructure (EC2, RDS, S3, API Gateway, Elastic Beanstalk)
-Automated CI/CD with AWS CodePipeline & CodeBuild

---

## 🛠️ Tech Stack

### **Frontend**
- Next.js  
- TailwindCSS  
- TypeScript  

### **Backend**
- Spring Boot  
- Hibernate / JPA  
- MySql
- Jwt Token

### **Infrastructure**
- AWS EC2  
- AWS RDS  
- AWS S3  
- AWS API Gateway  
- AWS Elastic Beanstalk  
- CI/CD: AWS CodePipeline + CodeBuild  

---

## 🖥️ Deployment
The app is fully deployed on AWS with:
- **EC2** for hosting
- **RDS** for database
- **S3** for media storage
- **API Gateway** for secure API access
- **Elastic Beanstalk** for scalable backend deployment

---

## 📸 Demo
> _Include screenshots, GIFs, or a demo video link here._

---

## 📦 Installation
```
Backend
bash
git clone [<backend-repo-url>](https://github.com/bipinstha07/Hotel_Booking)
cd Hotel_Booking
./mvnw spring-boot:run


Frontend
bash
git clone [<frontend-repo-url>](https://github.com/bipinstha07/Hotel_Booking_Frontend)
cd Hotel_Booking_Frontend
npm install
npm run dev

🔐 Environment Variables
Create .env files for frontend and backend with:
Frontend
NEXT_PUBLIC_API_URL=
NEXT_PUBLIC_STRIPE_PUBLISHABLE_KEY=

Backend
JWT_SECRET=
STRIPE_SECRET_KEY=
AWS_ACCESS_KEY_ID=
AWS_SECRET_ACCESS_KEY=
AWS_REGION=
DB_URL=
DB_USERNAME=
DB_PASSWORD=
