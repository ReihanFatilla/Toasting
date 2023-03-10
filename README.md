



# Toasting
### Toast / Dialog Android Library with a simple Implementation and Usage

![](https://jitpack.io/v/ReihanFatilla/Toasting.svg)

## Gradle
1. Add JitPack to your project build.gradle

```gradle
allprojects {
  repositories {
    ...
    maven { url 'https://jitpack.io' }
   }
}
```

#### or

If your Android studio version is Arctic Fox and above then add it in your settings.gradle:

```gradle
dependencyResolutionManagement {
repositories {
...
maven { url 'https://jitpack.io' }
}
}
```

2. Add the dependency in the application build.gradle

```gradle
dependencies {
  ...
  implementation 'com.github.ReihanFatilla:Toasting:0.1.0'
  }
```

### Usage

1. Show a sample Toasting

```kotlin
binding.btnSampleToasting.setOnClickListener {
    Toasting.Builder()
      .show(supportFragmentManager)
}
```

2. Customizable Toasting
```kotlin
binding.btnSampleToasting.setOnClickListener {
    Toasting.Builder(Toasting.ERROR_TYPE)
      .setTitleText("Validation Error!")
      .setContentText("Please try to validate again")
      .setButtonMessage("Try again")
      .setOnButtonClick {
      // Your code
      }
      .setTitleFont(getFont(R.font.nunito_bold))
      .setContentFont(getFont(R.font.nunito_regular))
      .setButtonFont(getFont(R.font.nunito_seminold))
      .show(supportFragmentManager)
}
```



## Contributing
Pull requests are welcome. For major changes, please open an issue first to discuss what you would like to change.

## License
[Apache 2.0](https://www.apache.org/licenses/LICENSE-2.0)
