# 🏨 Hotel Booking Application

A **full-stack hotel booking system** designed for privately-owned or large hotels, built with **Spring Boot**, **Next.js**, and **AWS**.  
This application provides a seamless booking experience with secure authentication, advanced filtering, online payments, and robust deployment on AWS.

---

## 🚀 Features
- **JWT-based Authentication** – Secure login & signup.
- **JPA Criteria Filtering** – Flexible search & filtering for rooms.
- **MapStruct DTOs** – Clean and efficient object mapping.
- **Stripe Payments** – Secure and fast payment processing.
- **Async Emails** – Booking confirmations & notifications.
- **Caching** – Faster data access.
- **Auditing** – Track system changes for security & logs.

---

## 🛠️ Tech Stack

### **Frontend**
- Next.js  
- TailwindCSS  
- TypeScript  

### **Backend**
- Spring Boot  
- Hibernate / JPA  
- MapStruct  

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

### **Backend**
```bash
git clone <backend-repo-url>
cd backend
./mvnw spring-boot:run


Frontend
bash
git clone <frontend-repo-url>
cd frontend
npm install
npm run dev

🔐 Environment Variables
Create .env files for frontend and backend with:
JWT_SECRET=
STRIPE_SECRET_KEY=
AWS_ACCESS_KEY_ID=
AWS_SECRET_ACCESS_KEY=
AWS_REGION=
DB_URL=
DB_USERNAME=
DB_PASSWORD=


If you want, I can make this README **GitHub-ready** by adding badges, a live demo link, and better screenshot placeholders.
