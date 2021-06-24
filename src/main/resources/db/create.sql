SET MODE PostgreSQL;

CREATE DATABASE news_portal;

CREATE TABLE users(id serial PRIMARY KEY,name VARCHAR,position VARCHAR,role VARCHAR,departmentId INTEGER);

CREATE TABLE news(id serial PRIMARY KEY,content VARCHAR,userId INTEGER,departmentId INTEGER);

CREATE TABLE  departments(id serial PRIMARY KEY,name VARCHAR,description VARCHAR);

CREATE TABLE  department_news(id serial PRIMARY KEY,departmentId INTEGER, newsId INTEGER);

CREATE TABLE  department_users(id serial PRIMARY KEY,departmentId INTEGER,userId INTEGER);

CREATE DATABASE news_portal_test WITH TEMPLATE news_portal;