# VaultKey IAM Controller

A Java-based **Identity and Access Management (IAM)** security simulator demonstrating enterprise-grade authentication, authorization, and audit logging principles.

---

## Overview

VaultKey IAM Controller is a desktop application that simulates core IAM functionality found in enterprise security systems. Built with Java Swing, it provides hands-on experience with **Role-Based Access Control (RBAC)**, **session management**, and **security event logging** – essential components of modern cybersecurity infrastructure.

The application enforces the **Principle of Least Privilege (PoLP)** by restricting access to sensitive resources based on authenticated user roles, while maintaining a comprehensive audit trail of all security events.

---

## Features

### 🔐 Authentication & Authorization
- **User Authentication**: Login system validates user credentials and establishes secure sessions
- **Role-Based Access Control (RBAC)**: Assigns permissions based on user roles (Admin/User)
- **Session Management**: Tracks authenticated users and enforces authorization checks throughout the session lifecycle

### 👤 Identity Management
- **User Provisioning**: Admin-only functionality to create new user accounts with assigned roles
- **Permission Validation**: Enforces role-based restrictions on administrative operations
- **Duplicate Prevention**: Validates usernames to prevent identity conflicts

### 🛡️ Security Controls
- **Privilege Escalation Detection**: Monitors and logs unauthorized attempts to access restricted functions
- **Access Enforcement**: Validates user permissions before granting access to sensitive resources
- **Principle of Least Privilege**: Standard users cannot access administrative functions or root-level resources

### 📋 Audit & Compliance
- **Real-Time Audit Logging**: Captures all authentication attempts, provisioning actions, and access requests
- **Timestamped Events**: Records precise timestamps for security event correlation
- **Tamper-Evident Logs**: Immutable log entries track authentication failures, privilege escalation attempts, and resource access

---

## Technical Stack

| Component          | Technology                              |
|--------------------|-----------------------------------------|
| **Language**       | Java (JDK 11+)                         |
| **GUI Framework**  | Java Swing (GridBagLayout)             |
| **Data Structure** | ArrayList with User objects            |
| **Architecture**   | Object-Oriented Programming (OOP)      |
| **Security Model** | RBAC with session-based authorization  |

---

## How It Works

### 1. **Initial Setup**
The system initializes with a default `admin` account pre-configured with administrative privileges.

### 2. **Authentication Flow**
- User enters credentials in the login interface
- System validates username against user database
- Successful authentication establishes a session and logs the event
- Failed attempts are logged as security events

### 3. **User Provisioning** (Admin Only)
- Authenticated admin can create new user accounts
- System validates admin privileges before allowing provisioning
- New users are assigned roles that determine their access permissions
- All provisioning actions are logged to the audit trail

### 4. **Resource Access Control**
- Users request access to protected resources
- System checks authenticated session and user role
- Access is granted or denied based on RBAC policies
- All access attempts (successful and denied) are logged

### 5. **Security Monitoring**
- Real-time audit log displays all security events
- Tracks authentication successes/failures
- Monitors privilege escalation attempts
- Records resource access requests

---

## Security Event Types

The audit log captures the following security events:

| Event Type | Description | Log Example |
|------------|-------------|-------------|
| **Authentication Success** | Valid user login | `SUCCESS: User 'alice' authenticated and session started` |
| **Authentication Failure** | Invalid login attempt | `FAILURE: Authentication attempt failed for user 'unknown'` |
| **User Provisioning** | New account created | `PROVISION: Admin created new identity 'bob' with role 'user'` |
| **Access Granted** | Authorized resource access | `ACCESS GRANTED: User 'admin' accessed Root Directory` |
| **Access Denied** | Unauthorized access attempt | `ACCESS DENIED: User 'bob' attempted to access Root Directory` |
| **Privilege Escalation** | Unauthorized admin action | `SECURITY ALERT: Privilege Escalation attempt by 'bob'` |

---

## Installation & Usage

### Prerequisites
- Java Development Kit (JDK) 11 or higher
- Java Runtime Environment (JRE)

### Running the Application

1. **Compile the source code:**
   ```bash
   javac IAMapp.java
   ```

2. **Run the application:**
   ```bash
   java IAMapp
   ```

3. **Default credentials:**
   - Username: `admin`
   - Role: `admin`

### Basic Workflow

1. **Login** as `admin` using the default credentials
2. **Provision new users** with assigned roles (Admin/User)
3. **Test access controls** by logging in as different users
4. **Monitor the audit log** to observe security events in real-time

---

## Code Structure

```
IAMapp.java
├── User class           # User entity with username and role properties
├── IAMapp class         # Main application GUI and security logic
│   ├── userDatabase     # ArrayList storing User objects
│   ├── currentUser      # Session tracking variable
│   ├── findUser()       # User lookup method
│   ├── checkAdminPrivilege()  # Authorization validation
│   └── logAction()      # Audit logging function
```

---

## Learning Outcomes

This project demonstrates practical understanding of:

- **Identity and Access Management (IAM)** fundamentals
- **Role-Based Access Control (RBAC)** implementation
- **Authentication vs. Authorization** security models
- **Session management** and state tracking
- **Security audit logging** for incident response
- **Privilege escalation** detection and prevention
- **Principle of Least Privilege (PoLP)** in access control
- **Object-Oriented Programming (OOP)** design patterns

---

## Author

**David Hanna**  
Cybersecurity Student | York University  
[LinkedIn](https://www.linkedin.com/in/davidhannaa/) | [GitHub](https://github.com/davidhnna)

---

## Acknowledgments

Built as part of hands-on cybersecurity training to understand enterprise IAM systems and security controls.
