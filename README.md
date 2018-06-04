# Android-Master-Project
This is a starter project when starting any fresh project.

## Note:

 - This project does not follow any Design Architectutral Patterns. I can assure you that you will love to use it. Its just a normal way to start any project, but comes with lots of shortcuts and that can reduce your huge amount of Code.
* Re-usability and Abstraction has been implemented at my best
* This architecture assumes that your activity's root layout is CoordinatorLayout with Id as "coordinatorLayout". I have considered this so that I can leverage the power of Material Design specifications

## Whats new about this architecture:

* BaseXXX classes:
  - Variety of BaseXXX classes to extend your Android Components like:
    - **BaseAppCompatActivity.java**: Every Activity class will be extending this class. Example: SplashActivity.java extends BaseAppCompatActivity.java
    - **BaseToolBarActivity.java**: This class itself extends BaseAppCompatActivity.java Every BaseAppCompatActivity that uses toolbar should extend this class. Example: LoginActivity.java extends BaseToolBarActivity.java
    - **BaseNavigationDrawerActivity.java**: This class itself extends BaseToolBarActivity.java. Any Activity that uses Navigation Drawer in its layout should extend this class. Example: HomeActivity.java extends BaseNavigationDrawerActivity.java
    - **BaseFragment.java**: Every Fragment should extend this class.
    - **BaseDialogFragment.java**: Every DaialogFragment should extend this class.
    - **BaseRecyclerAdapter.java**: This is a generic adapter I have created so that I dont have to create and manage separate files for different types of adapter. [Demo is shown here](https://gist.github.com/chintansoni202/3c61aea787ae4bd49f26adee9dd40a08).
  - Shorthand methods:
    - Launch Activity
    - Load Fragment
    - Make a network Call
    - Show Toasts / Snackbars
    - Show / Hide Progress Dialog
    - Show AlertDialog / ConfirmDialog
    - logXXX methods
    - Hide Keyboard
    - Start Service
    - Save / Retrieve SharedPreferences
    - Finishing Activities with (w/o) Sending Result
    - Searchng a fragment
  - And all of these shorthand methods can be accessed from different BaseXXX Android Components also.
  
* Network Calls: 
  - All the Network Calls happen on Android's Service Component which helps in keeping activities, fragments fat-free.
  - Separation of Concerns: All the things related to a particular Api Call is encapsulated in one file.
  
* Uses latest libraries:
  - Support Library: 25.3.0
  - Butterknife: 8.5.1
  - Glide: 3.7.1
  - Easy Permissions: 0.2.1
  - Retrofit: 2.1.0
  - OKHttp: 3.4.1
  - EventBus: 3.0.0
