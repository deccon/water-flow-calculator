## Author: 	Declan McConway
## Date: 	29th Feb 2016
## Purpose: River Flow Calculation

# Overview:
The application uses a RESTful API to receive measurements taken from the cross section in a river.
Once the river data has been collected, a user/client can calculate the river flow by sending 
a HTTP POST request to the following endpoint:

	POST    <hostName>:<portNumber>/water/flow/measurements
	
# Installation:

1.	Download project from google drive link
2.	Open the project folder in a command/terminal window
2.  Run 'mvn clean install' on the water-flow-calculator project
4. 	To start the application, go to the target folder and run:
	java -jar water-flow-calculator-0.0.1-SNAPSHOT.jar server classes/config.yml

# Example:

See sample_data.json to run test data through the application.

	POST    http://localhost:8080/water/flow/measurements

Response:
{
  "name": "South Seymour River",
  "location": "Columbia-Shuswap, BC",
  "date_entered": "27-02-2016 14:15",
  "measurements": [
    {
            "width": 5,			//meters
	        "depth": 1.62,		//meters
	        "velocity" : 1.15	//meters/second
    },
	{ 
		...
	}
	...
  ],
  "results": {
    "totolArea": 95.95,		// meters squared
    "totalVelocty": 10.27,	// meters/second
    "totalFlow": 124.01		// cubic meters/second
  }
}