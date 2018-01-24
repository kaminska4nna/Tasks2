#!/usr/bin/env bash

if ./runcrud.sh;
then open http://localhost:8080/crud/v1/task/getTasks
echo "OK"
else echo "There were errors."
fi