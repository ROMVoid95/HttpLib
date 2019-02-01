[![](https://jitpack.io/v/ROMVoid95/HttpLib.svg)](https://jitpack.io/#ROMVoid95/HttpLib)

# HttpLib

A Java library to create fast and simple HttpRequests.

### GET Request
Example request to [httpbin](https://httpbin.org)
```java
HttpRequestBuilder httpRequestBuilder = new HttpRequestBuilder("https://httpbin.org/get", RequestType.GET)
	.setRequestHeader(new RequestHeader().addField("User-Agent", "I'm a very cool user agent"))
	.addParameter("paramKey", "paramValue");
try {
	RequestResponse requestResponse = httpRequestBuilder.sendRequest();
	System.out.println("ResponseCode: " + requestResponse.getResponseCode());
	System.out.println("ResponseMessage: " + requestResponse.getResponseMessage());
} catch (IOException e) {
	e.printStackTrace();
}
```

### POST Request
Example request to [httpbin](https://httpbin.org)
```java
HttpRequestBuilder httpRequestBuilder = new HttpRequestBuilder("https://httpbin.org/post", RequestType.POST)
	.setRequestHeader(new RequestHeader().addField("User-Agent", "I'm a very cool user agent"))
	.addParameter("paramKey", "paramValue");
try {
	RequestResponse requestResponse = httpRequestBuilder.sendRequest();
	System.out.println("ResponseCode: " + requestResponse.getResponseCode());
	System.out.println("ResponseMessage: " + requestResponse.getResponseMessage());
} catch (IOException e) {
	e.printStackTrace();
}
```

## Get it!
Maven
```xml
<repository>
	<id>jitpack.io</id>
	<url>https://jitpack.io</url>
</repository>

<dependency>
	<groupId>com.github.ROMVoid95</groupId>
	<artifactId>HttpLib</artifactId>
	<version>VERSION</version>
</dependency>
```

Gradle
```gradle
repositories {
    ...
    maven { url 'https://jitpack.io' }
}

dependencies {
  compile 'com.github.ROMVoid95:HttpLib:VERSION'
}
```
you can find the version number in the [last releases](https://github.com/ROMVoid95/HttpLib/releases).
visit [this](https://jitpack.io/#ROMVoid95/HttpLib/) site to have an overview about all versions.

## Support
You can join my [discord server](https://discord.gg/Kq3YZRK) if you need help or want to have some informations about the library.
