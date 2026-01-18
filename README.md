# Secure-IAM-Controller 🔐

A Java-based **Identity and Access Management (IAM) Simulator** with **Role-Based Access Control (RBAC)**.

---

## 📌 Project Overview

This project is a functional security dashboard designed to simulate enterprise-grade user provisioning and access control. Built with **Java** and the **Swing** framework, it demonstrates the implementation of the **Principle of Least Privilege (PoLP)** and **stateful session management**.

The application provides a **Security Controller** interface where administrators can manage user identities, and the system enforces strict authorization checks before granting access to sensitive system resources.

---

## 🛡️ Key Security Features

* **Role-Based Access Control (RBAC)**
  Users are assigned specific roles (Admin, User) that determine their permissions within the system.

* **Session Management**
  The application tracks the `currentUser` state to prevent unauthorized actions by anonymous or low-privilege users.

* **Tamper-Evident Audit Logging**
  A real-time security log records every authentication success, failure, and privilege escalation attempt with high-resolution timestamps.

* **Privilege Escalation Protection**
  Hardened logic prevents standard users from accessing administrative functions or sensitive system "Root" resources.

---

## 💻 Technical Stack

| Component       | Details                                                       |
| --------------- | ------------------------------------------------------------- |
| Language        | Java (JDK 11+)                                                |
| GUI Framework   | Java Swing (GridBagLayout)                                    |
| Data Structures | HashMap (Identity Store), Map (Permission Mapping)            |
| Architecture    | Object-Oriented Programming (OOP) with modular security logic |

---

## 🚀 How It Works

* **Initial Access**
  The system boots with a default admin identity.

* **Authentication**
  Users authenticate via the Login module. Successes and failures are logged to the Audit Console.

* **User Provisioning**
  Only sessions with ADMIN privileges can utilize the Provision User method to add new identities to the HashMap identity store.

* **Resource Authorization**
  When a user attempts to Access Resource, the system performs a logic check against the session's role to grant or deny access.
