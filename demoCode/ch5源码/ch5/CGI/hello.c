/**
 * This C program is for a CGI script which generates
 * the output for a web page.  When displayed by a
 * browser, the message "Hello there!" will be shown
 * in blue.
 */
#include <stdio.h>

main(int argc, char *argv[]) {

    printf("Content-type: text/html%c%c",10,10);
    printf("<font color = blue>");
    printf("<H1>Hello here!  Good afternoon!</H1>");
	printf("<H1>Today is Monday!</H1>");
    printf("</font>");
}
