# ktlint-rules
Custom ktlint rules

## one-empty-line-after-class-header
Adds one empty line after class header. Companion objects are included to rule. 

# Usage
Library is published to jitpack.io. After adding implementation to your module, lint will include new rules. 

```
allprojects {
    repositories {
        ...
        maven { url 'https://jitpack.io' }
    }
}

dependencies {
    implementation 'com.github.kozaxinan:ktlint-rules:0.1'
}
```

# License
Copyright 2020 Kozaxinan - Sinan Kozak

Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the License. You may obtain a copy of the License at

http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions and limitations under the License.