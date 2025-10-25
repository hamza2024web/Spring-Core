# Spring User Management API

## Description

Cette application web Java basée sur **Spring Framework (Core + MVC)** permet la gestion des utilisateurs : création, consultation, mise à jour, suppression, authentification, gestion des rôles, validation stricte et pagination.  
Tout est réalisé **sans auto-configuration Spring Boot** : configuration manuelle via Java, architecture claire, modulaire et testable.

---

## Fonctionnalités

- **CRUD utilisateur** (Create, Read, Update, Delete)
- **Authentification** via endpoint `/api/users/login` (email + mot de passe hashé BCrypt)
- **Suppression logique** : champ `active` (les utilisateurs supprimés ne sont plus listés)
- **Validation stricte** : nom, email, mot de passe, rôle
- **Champ `createdAt`** : date de création de chaque user
- **Persistence JPA avec PostgreSQL**

---

## Modèle de données

**User**
- `id : Long`
- `name : String` (obligatoire, 3–100 caractères)
- `email : String` (obligatoire, format email, unique)
- `password : String` (stocké hashé, jamais exposé)
- `role : Enum (ADMIN, USER)` (obligatoire)
- `active : Boolean` (par défaut `true`)
- `createdAt : LocalDateTime` (rempli automatiquement)

---

## Endpoints principaux

| Méthode | URL                | Description                         |
|---------|--------------------|-------------------------------------|
| POST    | `/api/users`       | Créer un utilisateur                |
| GET     | `/api/users`       | Lister les utilisateurs  |
| GET     | `/api/users/{id}`  | Consulter un utilisateur par id     |
| PUT     | `/api/users/{id}`  | Modifier un utilisateur             |
| DELETE  | `/api/users/{id}`  | Suppression logique   |
| POST    | `/api/login`       | Authentification                    |

---

## Exemple de requêtes JSON

### Créer un utilisateur

```json
POST /api/users
{
  "name": "Hamza",
  "email": "hamza@email.com",
  "password": "azerty123",
  "role": "USER"
}
```

### Authentification

```json
POST /api/login
{
  "email": "hamza@email.com",
  "password": "azerty123"
}
```

---

## Architecture

- **Spring Core** : configuration Java (`@Configuration`, `@Bean`)
- **Spring MVC** : contrôleurs REST, validation, gestion des requêtes
- **Spring Data JPA** : persistance, transactions
- **DTO** : conversion entité ↔ DTO pour sécuriser les échanges (jamais de mot de passe en sortie)
- **Service** : logique métier (validation, unicité email, suppression logique)
- **Repository** : interface JpaRepository<User, Long>
- **Configuration** : fichiers `PersistenceConfig`, `WebConfig`, `WebAppInitializer` pour séparer les contextes

---

## Validation

- **Nom** : obligatoire, 3–100 caractères
- **Email** : format valide, unique
- **Password** : min 6 caractères
- **Role** : `ADMIN` ou `USER`
- **Active** : true par défaut

---

## Sécurité

- **Mot de passe hashé** avec BCrypt lors de la création
- **Authentification** : vérification du mot de passe hashé
- **Aucune donnée sensible exposée dans les DTO**

---

## Configuration

### **Base de données (PostgreSQL)**

Crée la base :
```sql
CREATE DATABASE user_management;
```

Dans `application.properties` :
```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/user_management
spring.datasource.username=postgres
spring.datasource.password=your_password
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

### **Dépendances Maven**

```xml
<dependency>
    <groupId>org.springframework</groupId>
    <artifactId>spring-context</artifactId>
    <version>5.x.x</version>
</dependency>
<dependency>
    <groupId>org.springframework</groupId>
    <artifactId>spring-webmvc</artifactId>
    <version>5.x.x</version>
</dependency>
<dependency>
    <groupId>org.springframework.data</groupId>
    <artifactId>spring-data-jpa</artifactId>
    <version>2.x.x</version>
</dependency>
<dependency>
    <groupId>org.postgresql</groupId>
    <artifactId>postgresql</artifactId>
    <version>42.x.x</version>
</dependency>
<dependency>
    <groupId>org.springframework.security</groupId>
    <artifactId>spring-security-crypto</artifactId>
    <version>5.x.x</version>
</dependency>
<dependency>
    <groupId>jakarta.validation</groupId>
    <artifactId>jakarta.validation-api</artifactId>
    <version>3.x.x</version>
</dependency>
```

---


### **Lancement local avec Docker**

**Exemple docker-compose pour Postgres** :
```yaml
version: '3.1'
services:
  db:
    image: postgres:15
    environment:
      POSTGRES_DB: user_management
      POSTGRES_USER: postgres
      POSTGRES_PASSWORD: your_password
    ports:
      - "5432:5432"
    volumes:
      - ./data:/var/lib/postgresql/data
```

---

## Tests API

Utilise **Postman** pour tester tous les endpoints :
- Création utilisateur
- Authentification
- Modification, suppression
- Vérifie que le mot de passe n’est jamais exposé

---

## Auteurs

- Hamza Akroubi
