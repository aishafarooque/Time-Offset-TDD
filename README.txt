Using test first development, write the program described below.

This program will read a list of European city names from a file and
display the number of hours difference between the time at that location and
GMT time.

The time difference can be found from querying the following URL:

http://worldtimeapi.org/api/timezone/Europe/CITYNAME.txt

For example,
http://worldtimeapi.org/api/timezone/Europe/Riga.txt
can be used to find the time offset for the city of Riga.

The offset is provided among the response as
raw_offset: 7200

Divide 7200 by 3600 and the result is 2 hours difference.

The program will read a list of city names and for each city print the
following:

Name of the city, hours offset.

For example

London 	      0
Riga 	      2
Amsterdam     ...
Minsk         ...
Brussels      ...
Kiev          ...
Warsaw        ...
Krakow        ...

If the service does not return a proper response, then --- will be displayed instead of
the number. If there was a network error to access the data, ERR will be printed
instead.

The program is designed in a way that
-we may use a different service to get the time offset
-we may read the list of cities from another source instead of a file


Required JUnit Import on IntelliK:
    org.junit.jupiter:junit-jupiter-engine:5.2.0
    org.mockito:mockito-core:2.23.0