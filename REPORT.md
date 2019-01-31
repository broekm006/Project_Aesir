# Final Report
```
Marc van den Broek  
12469602
```

This application allow a user to check new exercises, make his/her own schedule / list to work from. Calculate callories burned during cardio activities and hold a progression screen which checks the amount of weight used during each set.
<img src="https://github.com/broekm006/Project_Aesir/blob/master/doc/specific.png" height="40%" width="25%"/>

# High level overview (Navigation)
<img src="" height="100%" width="100%"/>

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
// todo

# Acknowledgements
Stackoverflow OP
