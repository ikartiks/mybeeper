# MyBeeper readme

Hi Dimitry,
Kindly go through this file before reviewing the project.

# Approach used

  - I first wrote a simple program (non android) to get the number of weekdays between 2 dates (So you will find some print messages commented out). This is done by 1st counting the number of weeks between 2 dates. For the remainder of days (eg 3 in case of 24days) i check when was my week started and based on it I add more weekdays.
  - For holidays, the logic is pretty straightforward, I insert all holidays in a database. For all holidays by default, a flag called canBeCarryForwarded is true. Only in case of holidays on weekends which cannot be forwarded this flag is false. So to get the number of days to subtract from weekend, you simply query all holidays in the date range with this flag as true. 
  - I found better logic to calculate after doing a little rnd but decided to stick with this as it is simple and easy to understand.

### Tech stack

Dillinger uses a number of open source projects to work properly:

* [androidx] - moving from android support libraries to androidx because of the reasons mentioned in links
* [espresso] - for unit testing, android testing  (2 tests written)
* [Room] - part of Jetpack, just wanted to show my proficiency in the same. [room_impl] You will find a better impl of room using app executors here, which is much more cleaner than the impl in this project as this one relies on creating threads. It also uses RxKotlin
* [ViewModel] - part of Jetpack, rotate the device and see all your data remain the same in configuration change.
* [ConstraintsLayouts] - using constraints rather than traditional android layouts


### Installation
Just clone  [github](https://github.com/ikartiks/mybeeper) in android studio and hit run

### Development note 
 - note that all strings and dimens are in respective places and reusable
 - all components are separate
 - rotate the device to see all data remains even on cpnfiguration change
 - Focus of development was getting the components in place, and NOT UI. It can be made better

### Todos
 - Write MORE Tests
 - Optimise if requierd



   [androidx]: <https://developer.android.com/jetpack/androidxr>
   [ConstraintsLayouts]: <https://developer.android.com/training/constraint-layout>
   [Room]: <https://developer.android.com/topic/libraries/architecture/room>
   [ViewModel]: <https://developer.android.com/topic/libraries/architecture/viewmodel/>
   [espresso]: <https://developer.android.com/training/testing/espresso>
   [room_impl]: <https://github.com/ikartiks/expenseTracker>
   