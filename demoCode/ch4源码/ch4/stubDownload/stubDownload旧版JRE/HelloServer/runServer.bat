SET JRE_BIN=E:\JRE_1.6\bin
SET JRE_LIB=E:\JRE_1.6\lib

%JRE_BIN%\java -Djava.security.policy=java.policy  -Djava.rmi.server.codebase=http://localhost:8080/stubs/ HelloServer 

pause
