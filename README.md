# Simple-App Kotlin/MVVM

#### The app has following root packages:
1. **api**: It contains all interfaces for external retrofit Api's
3. **data**: It contains all the data accessing and manipulating components.
4. **di**: Dependency providing classes using Dagger2.
5. **domain**: Contains Usecases and view Models
6. **presentation**: View classes along with their corresponding ViewModel.
7. **rx**: Classes focused on RxJava development
8. **utils**: Utility classes.

#### Core libraries used:
1. **RxJava**: Reactive components
2. **Room**: Android ORM for SQLLight
3. **Retrofit**: Works with rest client to provide Java interface of Rest Api's
4. **Dagger**: Dependency Injection
5. **Timber**: Better logging
6. **paging**: Paging library to improve performance when reading from local db
7. **Gson**: Used to deserialize and serialize data
8. **androidx.lifecycle**: Lifecycle components e.g. ViewModels and LiveData