# Final Report
```
Marc van den Broek  
12469602
```

// intro

# High level overview (Navigation)
// todo
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
- addEntry: Gets all the data from the spinners and adds them to the database under the correct list.
- getNameExercise: Loads the spinners with all the possible exercises that are available
- addNewField: Dynamically add new layout with spinner and button to allow the user to infinitly add new exercises.
- onDeleteClick: Delete the dynamically added layout and its contents.

ResultsListActivity:
- onClick: Loads the Weights or Cardio results screen based on which button is pressed.


# Challenges
// todo
# Acknowledgements
Stackoverflow OP
