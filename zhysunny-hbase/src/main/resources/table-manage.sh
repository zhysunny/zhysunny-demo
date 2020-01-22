#!/bin/bash

SCHEMA_NAME="ZHYSUNNY"
TEST_TABLE_NAME="TEST"
#创建schema
CREATE SCHEMA IF NOT EXISTS "ZHYSUNNY";

# zhysunny.test
CREATE TABLE IF NOT EXISTS "ZHYSUNNY"."TEST"(id unsigned_int not null,name VARCHAR not null,attr.age unsigned_int,appearance.height unsigned_double,CONSTRAINT PK PRIMARY KEY(id,name));
