# Final Report
```
Marc van den Broek  
12469602
```

This application allow a user to check new exercises, make his/her own schedule / list to work from. Calculate callories burned during cardio activities and hold a progression screen which checks the amount of weight used during each set.
<img src="https://github.com/broekm006/Project_Aesir/blob/master/doc/specific.png" height="40%" width="25%"/>

# High level overview (Navigation)
<img src="https://github.com/broekm006/Project_Aesir/blob/master/doc/Navigation.jpg" height="100%" width="100%"/>

# Class Diagram
<img src="https://github.com/broekm006/Project_Aesir/blob/master/doc/Class_Diagram_FINAL.jpg" height="100%" width="100%"/>

MainActivity:
- onOptionsItemSelected: Connects to the api using volley and stores through json the data in the database. (gotExercise, gotExerciseError, gotExerciseImg and gotExerciseImgError are used to achieve this)
- onButtonClick: Loads the ExerciseActivity
- onButtonCardioClick: Loads the CardioActivity
- onButtonPresetClick: Loads the PresetActivity
- onButtonResultsClick: Loads the ResultsListActivity
- onBackPressed: Closes the application

ExerciseActivity:
- onItemCLick: Loads the details of the item that where clicked and shows them in SpecificActivity.
- onResume: Reloads the listview to avoid an empty white page.

SpecificActivity:
- onOptionsItemSelected: Loads a textview, spinner and button to allow the user to add the given exercise directly to an exsiting list. 
- getNameList: Loads the spinner with every available list.
- onOkClick: Add the exercise to the list in the database and removes the added views.

CardioActivity:
- onSubmit: Enters the given data into the database.
- onButtonSelect: Switches between which activity is being entered and gives a visual about it.

PresetActivity:
- onOptionsItemSelected: Loads the NewListActivity.
- onItemClick: Loads the details of the item that where clicked and shows them in Preset_detail
- onItemLongClick: Delete given List + items that are stored within.

Preset_detail:
- onItemClick: Loads the details of the item that where clicked and shows them in PresetExercise.
- onItemLongClick: Delete given exercise from the list.

PresetExercise:
- onOptionsItemSelected: Loads TimerActivity.
- onClickSubmit: Submit the entered workout data to the database.

TimerActivity:
- onClick: Activates / deacitvates / lap times or pauses timer based on which button is clicked.

NewListActivity:
- addEntry: Gets all the data from the spinners and adds them to the database under the correct list.
- getNameExercise: Loads the spinners with all the possible exercises that are available
- addNewField: Dynamically add new layout with spinner and button to allow the user to infinitly add new exercises.
- onDeleteClick: Delete the dynamically added layout and its contents.

ResultsListActivity:
- onClick: Loads the Weights or Cardio results screen based on which button is pressed.

ResultsCardioActivity:
- calcCalories: Calculates the amount of callories burned based on the activity, speed and time.
- onConfetti: Releases a infinite stream of confetti to celebrate a workout.

ResultsWeightsActivity:
- onItemClick: Loads the details of the requested (clicked on) exercise.

ResutsActivity:
- generateGraph: Looks up training data from the database and generates a line & bar chart to display the progression.
- onAttempt: Releases a infinite stream of confetti to celebrate a workout.

# Challenges
At the start of this project i wanted to add a lot of features to make the application really shine vs its competitors and make it a app you would want to use on the daily. The idea was to make an application that on the basics did all the important stuff: Keep track of which activities are performed. Which weights are used and how many calories are burned with each set / km. If i had time left i would attempt to integrate a food intake program or an external tool such as a heart rate monitor, but while working on the application i realized how hard it was to store the data from the api and keep track of the results in a meaningful way. 

The api looked great online, but when i tried to retrieve all the data i ran into an issue. The api is made by a German and the main language is German because of this. There is an option in the api to choose your language, but because it is an home-made api instead of a community effort not everything get the same amount of attention in checkups. Because of this the language is not properly filtered. So when choosing the English language slot you essentially choose multi-language instead of English. So when looking through the exercises multiple languages will appear. 

Besides the language issue there is also the user input issue. This api accepts users from all over the world to input their own exercises to improve the vast list of exercises so everyone can benefit from it. Unfortunately there is no real standard everybody follows. Because of this not every exercise is useful. Some are just people testing the api so simple texts such as “test,test,test” appear instead of an actual description, exercises appear multiple times with the same name, but with different descriptions and most don’t upload and image or gif with the entry so there is no visual to see how the exercise is done.

Through filtering a few of the pointless exercises are removed from the list, but now i have to store which weights are used somewhere to keep track of progress. When making the design document and beginning of the application i did not think far enough ahead where and what i was going to store to keep track of progression. This resulted in me having to completely remodel my database as i was using it. I had to add several new tables to keep everything stored separate from each other while adding a foreign key to each new table.

The last real challenge i ran into was adding dynamic fields to create a new list. I used inflater layouts for this because i thought this worked smoothly, but since day 5 it seemed that storing the data that is stored in the spinner created by an inflater layout is not as simple as it seemed online. The same id/name was given to each new entry causing every request to give back the same thing. I tried several things such as storing data in a list (which causes multiple of the same), directly retrieving data (which gave the same every time), manipulating the selectedField when data was entered (This resulted in multiple of the first value) and some other actions. Through help from multiple TA’s and Stack Overflow i was able to uniquely give each new entry an ID so i could loop back over them when the submit button was clicked.

# Acknowledgements
Stackoverflow OP
