SET MODE PostgreSQL;

CREATE DATABASE news_portal;

CREATE TABLE IF NOT EXISTS users(id SERIAL PRIMARY KEY,name VARCHAR,position VARCHAR,role VARCHAR,departmentId int);

CREATE TABLE IF NOT EXISTS news(id SERIAL PRIMARY KEY,content VARCHAR,userId int,departmentId int);

CREATE IF NOT EXISTS departments(id SERIAL PRIMARY KEY,name VARCHAR,description VARCHAR);

CREATE IF NOT EXISTS department_news(id SERIAL PRIMARY KEY,departmentId int, newsId);

CREATE IF NOT EXISTS department_users(id SERIAL PRIMARY KEY,departmentId int,userId);

CREATE DATABASE news_portal_test WITH TEMPLATE news_portal;