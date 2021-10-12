/*Loel Nelson 
Answers and queries to the questions from Assignment1 
Layout = mysql query followed by the answer to the question*/

CREATE DATABASE Assignment1;
USE Assignment1;
/*Creating the database 

Creating the tables with constraints */
CREATE TABLE Location (
station_name varchar(20) PRIMARY KEY, latitude INT, longitude INT);

CREATE TABLE Wind (
station_name varchar(20), year int, month varchar (3), wind_speed DECIMAL (5,2),
PRIMARY KEY (station_name,year,month), 
FOREIGN KEY(station_name) REFERENCES Location(station_name));

CREATE TABLE Temperature (
station_name varchar (20), year int, month varchar(3), temperature DECIMAL (5,2), 
PRIMARY KEY(station_name,year,month), 
FOREIGN KEY (station_name) REFERENCES Location(station_name));
/*Loading the data from supplied .csv files */
LOAD DATA INFILE '/home/cloudera/Assignment1/data/location.csv' 
INTO TABLE Location 
FIELDS TERMINATED BY ',' 
LINES TERMINATED BY '\n';

LOAD DATA INFILE '/home/cloudera/Assignment1/data/wind.csv' 
INTO TABLE Wind 
FIELDS TERMINATED BY ',' 
LINES TERMINATED BY '\n';

LOAD DATA INFILE '/home/cloudera/Assignment1/data/temperature.csv' 
INTO TABLE Temperature 
FIELDS TERMINATED BY ',' 
LINES TERMINATED BY '\n';

CREATE VIEW Wind_Temp_View AS 
SELECT W.station_name, W.year, W.month, W.wind_speed, T.temperature 
FROM Wind W 
INNER JOIN Temperature T 
ON W.station_name = T.station_name AND W.year = T.year AND W.month = T.month;
/*Found this easier to select form when asked about wind and temperature*/

SELECT W.station_name, W.year, W.month, wind_speed, temperature 
FROM Wind W, Temperature T 
WHERE W.station_name = T.station_name AND W.year = T.year AND W.month = T.month;
/*This query produces 4704 results and doesn't show the full list in the console */

SELECT station_name, SUM(case when wind_speed != 0 then 1 else 0 end) as Valid_Wind, 
SUM(case when temperature!= 0 then 1 else 0 end) as Valid_Temp 
FROM Wind_Temp_View 
GROUP BY station_name;
/*station_name | Valid_Wind | Valid_Temp |
+--------------+------------+------------+
| Adelaide     |        152 |        146 |
| Clean_Air    |        199 |        202 |
| Faraday      |        778 |        769 |
| Grytviken    |        423 |       1121 |
| Halley       |        701 |        702 |
| Neumayer     |        410 |        410 |
| Rothera      |        457 |        450 |
| Signy        |        300 |        584 |
*/

SELECT station_name, year, 
SUM(case when wind_speed != 0 then 1 else 0 end) AS Valid_Wind_Report, 
SUM(case when temperature != 0 then 1 else 0 end) AS Valid_Temp_Report 
FROM Wind_Temp_View 
GROUP BY station_name, year;
/*Looking at the data there is a year for each of the 
stations when they did report a valid report for the year. 
The stations should have 12 for each year of reporting and at 
least 1 year per station did not report for either wind or temperature.
*/

SELECT station_name, month, SUM(case when wind_speed != 0 then 1 else 0 end) as Valid_Wind, 
SUM(case when temperature != 0 then 1 else 0 end) as Valid_Temp 
FROM Wind_Temp_View 
GROUP BY station_name, month;
/*station_name | month | Valid_Wind | Valid_Temp |
+--------------+-------+------------+------------+
| Adelaide     | apr   |         12 |         12 |
| Adelaide     | aug   |         13 |         13 |
| Adelaide     | dec   |         13 |         11 |
| Adelaide     | feb   |         12 |         11 |
| Adelaide     | jan   |         12 |          9 |
| Adelaide     | jul   |         13 |         13 |
| Adelaide     | jun   |         13 |         13 |
| Adelaide     | mar   |         12 |         12 |
| Adelaide     | may   |         13 |         13 |
| Adelaide     | nov   |         13 |         13 |
| Adelaide     | oct   |         13 |         13 |
| Adelaide     | sep   |         13 |         13 |
| Clean_Air    | apr   |         18 |         18 |
| Clean_Air    | aug   |         17 |         17 |
| Clean_Air    | dec   |         17 |         17 |
| Clean_Air    | feb   |         19 |         19 |
| Clean_Air    | jan   |         15 |         15 |
| Clean_Air    | jul   |         16 |         17 |
| Clean_Air    | jun   |         18 |         18 |
| Clean_Air    | mar   |         18 |         18 |
| Clean_Air    | may   |         18 |         18 |
| Clean_Air    | nov   |         16 |         16 |
| Clean_Air    | oct   |         13 |         14 |
| Clean_Air    | sep   |         14 |         15 |
| Faraday      | apr   |         66 |         66 |
| Faraday      | aug   |         64 |         65 |
| Faraday      | dec   |         65 |         62 |
| Faraday      | feb   |         65 |         63 |
| Faraday      | jan   |         65 |         62 |
| Faraday      | jul   |         64 |         65 |
| Faraday      | jun   |         64 |         65 |
| Faraday      | mar   |         65 |         63 |
| Faraday      | may   |         65 |         65 |
| Faraday      | nov   |         65 |         63 |
| Faraday      | oct   |         65 |         65 |
| Faraday      | sep   |         65 |         65 |
| Grytviken    | apr   |         35 |         93 |
| Grytviken    | aug   |         35 |         92 |
| Grytviken    | dec   |         34 |         95 |
| Grytviken    | feb   |         34 |         94 |
| Grytviken    | jan   |         36 |         95 |
| Grytviken    | jul   |         36 |         94 |
| Grytviken    | jun   |         38 |         96 |
| Grytviken    | mar   |         34 |         92 |
| Grytviken    | may   |         37 |         93 |
| Grytviken    | nov   |         34 |         93 |
| Grytviken    | oct   |         36 |         95 |
| Grytviken    | sep   |         34 |         89 |
| Halley       | apr   |         59 |         59 |
| Halley       | aug   |         57 |         58 |
| Halley       | dec   |         58 |         58 |
| Halley       | feb   |         59 |         59 |
| Halley       | jan   |         59 |         59 |
| Halley       | jul   |         58 |         58 |
| Halley       | jun   |         59 |         59 |
| Halley       | mar   |         59 |         59 |
| Halley       | may   |         59 |         59 |
| Halley       | nov   |         58 |         58 |
| Halley       | oct   |         58 |         58 |
| Halley       | sep   |         58 |         58 |
| Neumayer     | apr   |         35 |         35 |
| Neumayer     | aug   |         34 |         34 |
| Neumayer     | dec   |         34 |         34 |
| Neumayer     | feb   |         34 |         34 |
| Neumayer     | jan   |         33 |         33 |
| Neumayer     | jul   |         34 |         34 |
| Neumayer     | jun   |         35 |         35 |
| Neumayer     | mar   |         34 |         34 |
| Neumayer     | may   |         35 |         35 |
| Neumayer     | nov   |         34 |         34 |
| Neumayer     | oct   |         34 |         34 |
| Neumayer     | sep   |         34 |         34 |
| Rothera      | apr   |         39 |         39 |
| Rothera      | aug   |         36 |         37 |
| Rothera      | dec   |         39 |         34 |
| Rothera      | feb   |         38 |         36 |
| Rothera      | jan   |         38 |         37 |
| Rothera      | jul   |         37 |         37 |
| Rothera      | jun   |         39 |         39 |
| Rothera      | mar   |         38 |         38 |
| Rothera      | may   |         39 |         39 |
| Rothera      | nov   |         38 |         38 |
| Rothera      | oct   |         38 |         38 |
| Rothera      | sep   |         38 |         38 |
| Signy        | apr   |         25 |         49 |
| Signy        | aug   |         26 |         49 |
| Signy        | dec   |         25 |         48 |
| Signy        | feb   |         24 |         49 |
| Signy        | jan   |         25 |         49 |
| Signy        | jul   |         25 |         49 |
| Signy        | jun   |         25 |         49 |
| Signy        | mar   |         25 |         48 |
| Signy        | may   |         25 |         49 |
| Signy        | nov   |         25 |         47 |
| Signy        | oct   |         25 |         49 |
| Signy        | sep   |         25 |         49 |
*/

SELECT W.station_name, min(W.year), min(T.year)
FROM Wind W, Temperature T 
WHERE wind_speed != 0 and temperature ! = 0 and W.station_name = T.station_name
GROUP BY W.station_name;
/*Station	Wind Start	Temp Start
Adelaide	1962		1962
Clean Air	1986		1986
Faraday		1950		1950
Grytviken	1959		1905
Halley		1957		1957
Neumayer	1981		1981
Rothera		1976		1976
Signy  		1956		1947

*/


SELECT station_name AS Station, MAX(temperature) AS MIN_Temp, MAX(temperature) AS MAX_Temp 
FROM Temperature 
GROUP BY station_name;
/*Adelaide	 -20.1  and 2.10
Clean Air	 -66.6  and 0 
Faraday 	 -20.1  and 2.4
Grytviken 	 -6.2   and 8.40
Halley 	 	 -36.70 and 0
Neumayer 	 -33.40 and 0
Rothera 	 -20.5  and 2.7
Signy  	 	 -17.3  and 3.2

*/

SELECT station_name, MIN(temperature) as MIN_Temp, MAX(temperature) as MAX_Temp
FROM Temperature 
WHERE year = 2000;
/*
The max and min temperature are -62.2 and 1.10 for Clean_Air station 
*/

SELECT L.station_name, AVG(wind_speed) 
FROM Wind, Location L
WHERE latitude = 90 AND Wind.station_name = Location.station_name;
/*The average wind speed for 90-degree latitude is 6.67 from the 
Clean Air weather station.*/

SELECT station_name,year,month, temperature 
FROM Temperature 
WHERE station_name = 'Clean_Air' 
ORDER BY temperature ASC;
/*From looking at the data my guess for when it is summer is between
 November – February and winter is from May – September .*/

SELECT station_name,
MAX(temperature) AS MAX_Temp, 
MIN(temperature) AS MIN_Temp,
AVG(temperature) AS AVG_Temp  
FROM Temperature 
WHERE month = 'jun' 
GROUP BY station_name;
/*Being that these stations are in the southern hemisphere 
I believe the seasons are opposite from us in the north.  
So, I picked June as the winter month and around December 
for the summer months. The min, max and average temps for 
all stations during the month of June:
*/

