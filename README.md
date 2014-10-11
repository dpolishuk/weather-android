### What is this repository for? ###

* This is weather android app which uses worldweatheronline.com as data provider
* v1.0.0

### How do I get set up? ###

* ./gradlew clean build
* and don't forget to setup your sdk.dir (via local.properties or direct specification)

### Design concepts ###

The whole design of my app is based on Dependency Injections and EventBus concepts.
I wanted to split all classes out from each other onto independent components.

Interaction with weather web-service is based on fetching forecast by latitude and longitude.
I use shared preferences as a cache for forecast responses and update this cache in two cases:

1. user refreshed the list manually by pull-to-refresh action on the list of data
2. time of response is expired (it's 24 hours) and in the next scrolling action - application will try to fetch new data for this location

### Dependencies ###
I used a lot of 3rd libraries and the main reason why I did it, just to speed up development process.

* rxjava - just to not hassle with AsyncTask
* retrofit - quick bootstrap of any http webservice stub
* ormlite - quick database juggling
* otto - eventbus
* dagger - DI
* com.etsy.android.grid - Staggered GridView for weather dashboard
* timber - very customizable logger for android

### Which design patterns I used ###
DI, EventBus, Observer, Builder

Applications consists of two screens:

 1. main screen with weather dashboard
 2. settings activity where the user can specify Temperature Units (Celcius or Fahrenheit)

Small clarification: To refresh weathers list - just pull down (I used SwipeRefreshLayout from support library)