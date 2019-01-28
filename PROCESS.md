# day 1  
Made proposal document and created the new android application (connected to git for version control).

# day 2  
Made design document with class diagram, feature overview and data connections. Started to look deeper into the structure(s) of the application and planned out the basic methods.

# day 3  
Work on initial prototype for the application. Anroid had a small issue with accepting input & gradle syncing. Fixed by reconfiguring android. Created most activities that the app requires. Started with creating the api call to get the main data.

# day 4  
Work on implementiation of the database + listview for presets. > database for presets contains the name and will have room for the new exercises that will be added to the database. img was broken > fixed by changing some android settings.

# day 5  
To allow presets to add new exercises multiple fields will be needed. To achieve this dynamic fiels will be added to the new list creation. > Struggle with my dynamic fields (presets), but seems to work. Only need to retreive data and connected correct spinner data to the new fieds. && Long click list remove doesnt work (id always seems to be 1) Primary key does not get assigned properly.

# day 6  
Fixed issue with primary key & created the spinner data for the presets. removed data entry and added id fields to JSON data to ensure exercise always has a unique id known in the api.

# day 7  
Transforming JSON data to database. Reloading every exercise with json works, but creates a form of lag. The user has to wait untill the full list is retreived from the api. Chaning to the db will make this all local and avoid the loading problem. Instead an update button will be added to ensure new data can always be gathered. Created style guide with "team A"

# day 8  
Finished reform from JSON to database. Data is now shown corretly through the listview interface and only stored 1 time instead of multiple times. Memory leak problems sovled by closing certain cursors after use. 

# day 9
INTERSHIP STUFF

# day 10
Database did not work as initially thought about. So i remoddeld the entire database sturcture and changed the code accordingly. Now it hold room for the exercises, lists and exercises within the lists.

# day 11
Issue with listview (Presets) when data is clicked the list dissapears. Normal cursor swap does not work. Talked with Renske about a workaround. Now it recreates the data on onResume / onUpdate.

# day 12
Created cardio as a activity + data storage of weights and cardio are now possible through the correct locations. Data is stored in the database. Only need to check if date/time storage need to be added to the data entries. Further fixed issues with dissapearing listviews through the onResume function.

# day 13
Removed empty lines from database after json request. > need new way to get data from parent layout inflater. Current way creates double entries. When trying to cast inflated object errors occur.

# day 14 "h4ck"aton
Added notification screens when input is required but not entered, made requirements for adding data (there actually needs to be data, no empty entries). testing with end-users > gather input and application bugs. And attempt to rework application to user feedback. Participated in the "hack"aton.

# day 15
Added cardio results after input + pie chart to see calories burned. Updated general designs + cleaned code. FINNALLY fixed add exercise issue. Now correctly enters the exercise from dynamic inflater fields without overwriting the values. 

# day 16
updated design, migitated buttons to the action bar to free up space in the activities. added comments to chunks of code.
