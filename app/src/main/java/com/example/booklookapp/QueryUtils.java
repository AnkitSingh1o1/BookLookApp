package com.example.booklookapp;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Helper methods related to requesting and receiving earthquake data from USGS.
 */
public final class QueryUtils {

    /** Sample JSON response for a USGS query */
    private static final String SAMPLE_JSON_RESPONSE ="{\n" +
            "  \"kind\": \"books#volumes\",\n" +
            "  \"totalItems\": 1445,\n" +
            "  \"items\": [\n" +
            "    {\n" +
            "      \"kind\": \"books#volume\",\n" +
            "      \"id\": \"Wu5qDwAAQBAJ\",\n" +
            "      \"etag\": \"HXaBzJivWds\",\n" +
            "      \"selfLink\": \"https://www.googleapis.com/books/v1/volumes/Wu5qDwAAQBAJ\",\n" +
            "      \"volumeInfo\": {\n" +
            "        \"title\": \"Professional Android\",\n" +
            "        \"authors\": [\n" +
            "          \"Reto Meier\",\n" +
            "          \"Ian Lake\"\n" +
            "        ],\n" +
            "        \"publisher\": \"John Wiley & Sons\",\n" +
            "        \"publishedDate\": \"2018-08-23\",\n" +
            "        \"description\": \"The comprehensive developer guide to the latest Android featuresand capabilities Professional Android, 4th Edition shows developers how toleverage the latest features of Android to create robust andcompelling mobile apps. This hands-on approach provides in-depthcoverage through a series of projects, each introducing a newAndroid platform feature and highlighting the techniques and bestpractices that exploit its utmost functionality. The exercisesbegin simply, and gradually build into advanced Androiddevelopment. Clear, concise examples show you how to quicklyconstruct real-world mobile applications. This book is your guide to smart, efficient, effective Androiddevelopment. Learn the best practices that get more out of Android Understand the anatomy, lifecycle, and UI metaphor of Androidapps Design for all mobile platforms, including tablets Utilize both the Android framework and Google Playservices\",\n" +
            "        \"industryIdentifiers\": [\n" +
            "          {\n" +
            "            \"type\": \"ISBN_13\",\n" +
            "            \"identifier\": \"9781118949535\"\n" +
            "          },\n" +
            "          {\n" +
            "            \"type\": \"ISBN_10\",\n" +
            "            \"identifier\": \"1118949536\"\n" +
            "          }\n" +
            "        ],\n" +
            "        \"readingModes\": {\n" +
            "          \"text\": true,\n" +
            "          \"image\": true\n" +
            "        },\n" +
            "        \"pageCount\": 928,\n" +
            "        \"printType\": \"BOOK\",\n" +
            "        \"categories\": [\n" +
            "          \"Computers\"\n" +
            "        ],\n" +
            "        \"maturityRating\": \"NOT_MATURE\",\n" +
            "        \"allowAnonLogging\": true,\n" +
            "        \"contentVersion\": \"0.1.1.0.preview.3\",\n" +
            "        \"panelizationSummary\": {\n" +
            "          \"containsEpubBubbles\": false,\n" +
            "          \"containsImageBubbles\": false\n" +
            "        },\n" +
            "        \"imageLinks\": {\n" +
            "          \"smallThumbnail\": \"http://books.google.com/books/content?id=Wu5qDwAAQBAJ&printsec=frontcover&img=1&zoom=5&edge=curl&source=gbs_api\",\n" +
            "          \"thumbnail\": \"http://books.google.com/books/content?id=Wu5qDwAAQBAJ&printsec=frontcover&img=1&zoom=1&edge=curl&source=gbs_api\"\n" +
            "        },\n" +
            "        \"language\": \"en\",\n" +
            "        \"previewLink\": \"http://books.google.co.in/books?id=Wu5qDwAAQBAJ&printsec=frontcover&dq=android&hl=&cd=1&source=gbs_api\",\n" +
            "        \"infoLink\": \"https://play.google.com/store/books/details?id=Wu5qDwAAQBAJ&source=gbs_api\",\n" +
            "        \"canonicalVolumeLink\": \"https://play.google.com/store/books/details?id=Wu5qDwAAQBAJ\"\n" +
            "      },\n" +
            "      \"saleInfo\": {\n" +
            "        \"country\": \"IN\",\n" +
            "        \"saleability\": \"FOR_SALE\",\n" +
            "        \"isEbook\": true,\n" +
            "        \"listPrice\": {\n" +
            "          \"amount\": 4380.16,\n" +
            "          \"currencyCode\": \"INR\"\n" +
            "        },\n" +
            "        \"retailPrice\": {\n" +
            "          \"amount\": 4380.16,\n" +
            "          \"currencyCode\": \"INR\"\n" +
            "        },\n" +
            "        \"buyLink\": \"https://play.google.com/store/books/details?id=Wu5qDwAAQBAJ&rdid=book-Wu5qDwAAQBAJ&rdot=1&source=gbs_api\",\n" +
            "        \"offers\": [\n" +
            "          {\n" +
            "            \"finskyOfferType\": 1,\n" +
            "            \"listPrice\": {\n" +
            "              \"amountInMicros\": 4380160000,\n" +
            "              \"currencyCode\": \"INR\"\n" +
            "            },\n" +
            "            \"retailPrice\": {\n" +
            "              \"amountInMicros\": 4380160000,\n" +
            "              \"currencyCode\": \"INR\"\n" +
            "            }\n" +
            "          }\n" +
            "        ]\n" +
            "      },\n" +
            "      \"accessInfo\": {\n" +
            "        \"country\": \"IN\",\n" +
            "        \"viewability\": \"PARTIAL\",\n" +
            "        \"embeddable\": true,\n" +
            "        \"publicDomain\": false,\n" +
            "        \"textToSpeechPermission\": \"ALLOWED\",\n" +
            "        \"epub\": {\n" +
            "          \"isAvailable\": true,\n" +
            "          \"acsTokenLink\": \"http://books.google.co.in/books/download/Professional_Android-sample-epub.acsm?id=Wu5qDwAAQBAJ&format=epub&output=acs4_fulfillment_token&dl_type=sample&source=gbs_api\"\n" +
            "        },\n" +
            "        \"pdf\": {\n" +
            "          \"isAvailable\": true,\n" +
            "          \"acsTokenLink\": \"http://books.google.co.in/books/download/Professional_Android-sample-pdf.acsm?id=Wu5qDwAAQBAJ&format=pdf&output=acs4_fulfillment_token&dl_type=sample&source=gbs_api\"\n" +
            "        },\n" +
            "        \"webReaderLink\": \"http://play.google.com/books/reader?id=Wu5qDwAAQBAJ&hl=&printsec=frontcover&source=gbs_api\",\n" +
            "        \"accessViewStatus\": \"SAMPLE\",\n" +
            "        \"quoteSharingAllowed\": false\n" +
            "      },\n" +
            "      \"searchInfo\": {\n" +
            "        \"textSnippet\": \"Clear, concise examples show you how to quickly construct real-world mobile applications. This book is your guide to smart, efficient, effective Android development.\"\n" +
            "      }\n" +
            "    },\n" +
            "    {\n" +
            "      \"kind\": \"books#volume\",\n" +
            "      \"id\": \"oMYQz4_BW48C\",\n" +
            "      \"etag\": \"PmLEisEru4Y\",\n" +
            "      \"selfLink\": \"https://www.googleapis.com/books/v1/volumes/oMYQz4_BW48C\",\n" +
            "      \"volumeInfo\": {\n" +
            "        \"title\": \"Learning Android\",\n" +
            "        \"authors\": [\n" +
            "          \"Marko Gargenta\"\n" +
            "        ],\n" +
            "        \"publisher\": \"\\\"O'Reilly Media, Inc.\\\"\",\n" +
            "        \"publishedDate\": \"2011-03-10\",\n" +
            "        \"description\": \"Want to build apps for Android devices? This book is the perfect way to master the fundamentals. Written by an expert who's taught this mobile platform to hundreds of developers in large organizations, this gentle introduction shows experienced object-oriented programmers how to use Android’s basic building blocks to create user interfaces, store data, connect to the network, and more. You'll build a Twitter-like application throughout the course of this book, adding new features with each chapter. Along the way, you'll also create your own toolbox of code patterns to help you program any type of Android application with ease. Get an overview of the Android platform and discover how it fits into the mobile ecosystem Learn about the Android stack, including its application framework, and the structure and distribution of application packages (APK) Set up your Android development environment and get started with simple programs Use Android’s building blocks—Activities, Intents, Services, Content Providers, and Broadcast Receivers Learn how to build basic Android user interfaces and organize UI elements in Views and Layouts Build a service that uses a background process to update data in your application Get an introduction to Android Interface Definition Language (AIDL) and the Native Development Kit (NDK)\",\n" +
            "        \"industryIdentifiers\": [\n" +
            "          {\n" +
            "            \"type\": \"ISBN_13\",\n" +
            "            \"identifier\": \"9781449307240\"\n" +
            "          },\n" +
            "          {\n" +
            "            \"type\": \"ISBN_10\",\n" +
            "            \"identifier\": \"1449307248\"\n" +
            "          }\n" +
            "        ],\n" +
            "        \"readingModes\": {\n" +
            "          \"text\": true,\n" +
            "          \"image\": true\n" +
            "        },\n" +
            "        \"pageCount\": 270,\n" +
            "        \"printType\": \"BOOK\",\n" +
            "        \"categories\": [\n" +
            "          \"Computers\"\n" +
            "        ],\n" +
            "        \"averageRating\": 4,\n" +
            "        \"ratingsCount\": 7,\n" +
            "        \"maturityRating\": \"NOT_MATURE\",\n" +
            "        \"allowAnonLogging\": true,\n" +
            "        \"contentVersion\": \"0.3.2.0.preview.3\",\n" +
            "        \"imageLinks\": {\n" +
            "          \"smallThumbnail\": \"http://books.google.com/books/content?id=oMYQz4_BW48C&printsec=frontcover&img=1&zoom=5&edge=curl&source=gbs_api\",\n" +
            "          \"thumbnail\": \"http://books.google.com/books/content?id=oMYQz4_BW48C&printsec=frontcover&img=1&zoom=1&edge=curl&source=gbs_api\"\n" +
            "        },\n" +
            "        \"language\": \"en\",\n" +
            "        \"previewLink\": \"http://books.google.co.in/books?id=oMYQz4_BW48C&pg=PP1&dq=android&hl=&cd=2&source=gbs_api\",\n" +
            "        \"infoLink\": \"http://books.google.co.in/books?id=oMYQz4_BW48C&dq=android&hl=&source=gbs_api\",\n" +
            "        \"canonicalVolumeLink\": \"https://books.google.com/books/about/Learning_Android.html?hl=&id=oMYQz4_BW48C\"\n" +
            "      },\n" +
            "      \"saleInfo\": {\n" +
            "        \"country\": \"IN\",\n" +
            "        \"saleability\": \"NOT_FOR_SALE\",\n" +
            "        \"isEbook\": false\n" +
            "      },\n" +
            "      \"accessInfo\": {\n" +
            "        \"country\": \"IN\",\n" +
            "        \"viewability\": \"PARTIAL\",\n" +
            "        \"embeddable\": true,\n" +
            "        \"publicDomain\": false,\n" +
            "        \"textToSpeechPermission\": \"ALLOWED\",\n" +
            "        \"epub\": {\n" +
            "          \"isAvailable\": true\n" +
            "        },\n" +
            "        \"pdf\": {\n" +
            "          \"isAvailable\": true\n" +
            "        },\n" +
            "        \"webReaderLink\": \"http://play.google.com/books/reader?id=oMYQz4_BW48C&hl=&printsec=frontcover&source=gbs_api\",\n" +
            "        \"accessViewStatus\": \"SAMPLE\",\n" +
            "        \"quoteSharingAllowed\": false\n" +
            "      },\n" +
            "      \"searchInfo\": {\n" +
            "        \"textSnippet\": \"Want to build apps for Android devices? This book is the perfect way to master the fundamentals.\"\n" +
            "      }\n" +
            "    },\n" +
            "    {\n" +
            "      \"kind\": \"books#volume\",\n" +
            "      \"id\": \"nDqkBgAAQBAJ\",\n" +
            "      \"etag\": \"/ZqC8gShYtg\",\n" +
            "      \"selfLink\": \"https://www.googleapis.com/books/v1/volumes/nDqkBgAAQBAJ\",\n" +
            "      \"volumeInfo\": {\n" +
            "        \"title\": \"Android App Development For Dummies\",\n" +
            "        \"authors\": [\n" +
            "          \"Michael Burton\"\n" +
            "        ],\n" +
            "        \"publisher\": \"John Wiley & Sons\",\n" +
            "        \"publishedDate\": \"2015-03-09\",\n" +
            "        \"description\": \"The updated edition of the bestselling guide to Android app development If you have ambitions to build an Android app, this hands-on guide gives you everything you need to dig into the development process and turn your great idea into a reality! In this new edition of Android App Development For Dummies, you'll find easy-to-follow access to the latest programming techniques that take advantage of the new features of the Android operating system. Plus, two programs are provided: a simple program to get you started and an intermediate program that uses more advanced aspects of the Android platform. Android mobile devices currently account for nearly 80% of mobile phone market share worldwide, making it the best platform to reach the widest possible audience. With the help of this friendly guide, developers of all stripes will quickly find out how to install the tools they need, design a good user interface, grasp the design differences between phone and tablet applications, handle user input, avoid common pitfalls, and turn a \\\"meh\\\" app into one that garners applause. Create seriously cool apps for the latest Android smartphones and tablets Adapt your existing apps for use on an Android device Start working with programs and tools to create Android apps Publish your apps to the Google Play Store Whether you're a new or veteran programmer, Android App Development For Dummies will have you up and running with the ins and outs of the Android platform in no time.\",\n" +
            "        \"industryIdentifiers\": [\n" +
            "          {\n" +
            "            \"type\": \"ISBN_13\",\n" +
            "            \"identifier\": \"9781119017929\"\n" +
            "          },\n" +
            "          {\n" +
            "            \"type\": \"ISBN_10\",\n" +
            "            \"identifier\": \"1119017920\"\n" +
            "          }\n" +
            "        ],\n" +
            "        \"readingModes\": {\n" +
            "          \"text\": false,\n" +
            "          \"image\": true\n" +
            "        },\n" +
            "        \"pageCount\": 432,\n" +
            "        \"printType\": \"BOOK\",\n" +
            "        \"categories\": [\n" +
            "          \"Computers\"\n" +
            "        ],\n" +
            "        \"maturityRating\": \"NOT_MATURE\",\n" +
            "        \"allowAnonLogging\": false,\n" +
            "        \"contentVersion\": \"1.15.3.0.preview.1\",\n" +
            "        \"panelizationSummary\": {\n" +
            "          \"containsEpubBubbles\": false,\n" +
            "          \"containsImageBubbles\": false\n" +
            "        },\n" +
            "        \"imageLinks\": {\n" +
            "          \"smallThumbnail\": \"http://books.google.com/books/content?id=nDqkBgAAQBAJ&printsec=frontcover&img=1&zoom=5&edge=curl&source=gbs_api\",\n" +
            "          \"thumbnail\": \"http://books.google.com/books/content?id=nDqkBgAAQBAJ&printsec=frontcover&img=1&zoom=1&edge=curl&source=gbs_api\"\n" +
            "        },\n" +
            "        \"language\": \"en\",\n" +
            "        \"previewLink\": \"http://books.google.co.in/books?id=nDqkBgAAQBAJ&printsec=frontcover&dq=android&hl=&cd=3&source=gbs_api\",\n" +
            "        \"infoLink\": \"http://books.google.co.in/books?id=nDqkBgAAQBAJ&dq=android&hl=&source=gbs_api\",\n" +
            "        \"canonicalVolumeLink\": \"https://books.google.com/books/about/Android_App_Development_For_Dummies.html?hl=&id=nDqkBgAAQBAJ\"\n" +
            "      },\n" +
            "      \"saleInfo\": {\n" +
            "        \"country\": \"IN\",\n" +
            "        \"saleability\": \"NOT_FOR_SALE\",\n" +
            "        \"isEbook\": false\n" +
            "      },\n" +
            "      \"accessInfo\": {\n" +
            "        \"country\": \"IN\",\n" +
            "        \"viewability\": \"PARTIAL\",\n" +
            "        \"embeddable\": true,\n" +
            "        \"publicDomain\": false,\n" +
            "        \"textToSpeechPermission\": \"ALLOWED\",\n" +
            "        \"epub\": {\n" +
            "          \"isAvailable\": false\n" +
            "        },\n" +
            "        \"pdf\": {\n" +
            "          \"isAvailable\": false\n" +
            "        },\n" +
            "        \"webReaderLink\": \"http://play.google.com/books/reader?id=nDqkBgAAQBAJ&hl=&printsec=frontcover&source=gbs_api\",\n" +
            "        \"accessViewStatus\": \"SAMPLE\",\n" +
            "        \"quoteSharingAllowed\": false\n" +
            "      },\n" +
            "      \"searchInfo\": {\n" +
            "        \"textSnippet\": \"In this new edition of Android App Development For Dummies, you&#39;ll find easy-to-follow access to the latest programming techniques that take advantage of the new features of the Android operating system.\"\n" +
            "      }\n" +
            "    },\n" +
            "    {\n" +
            "      \"kind\": \"books#volume\",\n" +
            "      \"id\": \"M7ngCAAAQBAJ\",\n" +
            "      \"etag\": \"vMbHyeieGTc\",\n" +
            "      \"selfLink\": \"https://www.googleapis.com/books/v1/volumes/M7ngCAAAQBAJ\",\n" +
            "      \"volumeInfo\": {\n" +
            "        \"title\": \"Teach Yourself VISUALLY Android Phones and Tablets\",\n" +
            "        \"authors\": [\n" +
            "          \"Guy Hart-Davis\"\n" +
            "        ],\n" +
            "        \"publisher\": \"John Wiley & Sons\",\n" +
            "        \"publishedDate\": \"2015-07-07\",\n" +
            "        \"description\": \"Experience all your Android device has to offer! Teach Yourself VISUALLY Android Phones and Tablets, 2nd Edition is the perfect resource if you are a visual learner who wants to master the ins and outs of the Android operating system. With step-by-step instructions driven by targeted, easy-to-understand graphics, this informative book shines a light on the features, functions, and quirks of the Android OS—and shows you how to use them. With the guidance provided by this easy to follow resource, you will quickly access, download, and enjoy books, apps, music, and video content, as well as photos, emails, and other forms of media, right from your phone or tablet! This book is perfect for Android users at beginner to intermediate levels. The Android operating system is graphics intensive, which is why a visual guide is the best way to navigate your Android device. Now that the Android OS is available on both phones and tablets, you can maximize the productivity and convenience of your devices by mastering the features, functions, and quirks of this operating system. Explore the latest Android features and functions Peruse full-color illustrations that walk you, step-by-step, through instructions for using the Android operating system Discover how to access, download, and enjoy multimedia content Sync your Android devices to maximize their capabilities Teach Yourself VISUALLY Android Phones and Tablets, 2nd Edition is the top resource for visual learners wanting to further explore the capabilities of Android devices.\",\n" +
            "        \"industryIdentifiers\": [\n" +
            "          {\n" +
            "            \"type\": \"ISBN_13\",\n" +
            "            \"identifier\": \"9781119116769\"\n" +
            "          },\n" +
            "          {\n" +
            "            \"type\": \"ISBN_10\",\n" +
            "            \"identifier\": \"1119116767\"\n" +
            "          }\n" +
            "        ],\n" +
            "        \"readingModes\": {\n" +
            "          \"text\": false,\n" +
            "          \"image\": true\n" +
            "        },\n" +
            "        \"pageCount\": 320,\n" +
            "        \"printType\": \"BOOK\",\n" +
            "        \"categories\": [\n" +
            "          \"Computers\"\n" +
            "        ],\n" +
            "        \"maturityRating\": \"NOT_MATURE\",\n" +
            "        \"allowAnonLogging\": false,\n" +
            "        \"contentVersion\": \"1.10.2.0.preview.1\",\n" +
            "        \"panelizationSummary\": {\n" +
            "          \"containsEpubBubbles\": false,\n" +
            "          \"containsImageBubbles\": false\n" +
            "        },\n" +
            "        \"imageLinks\": {\n" +
            "          \"smallThumbnail\": \"http://books.google.com/books/content?id=M7ngCAAAQBAJ&printsec=frontcover&img=1&zoom=5&edge=curl&source=gbs_api\",\n" +
            "          \"thumbnail\": \"http://books.google.com/books/content?id=M7ngCAAAQBAJ&printsec=frontcover&img=1&zoom=1&edge=curl&source=gbs_api\"\n" +
            "        },\n" +
            "        \"language\": \"en\",\n" +
            "        \"previewLink\": \"http://books.google.co.in/books?id=M7ngCAAAQBAJ&printsec=frontcover&dq=android&hl=&cd=4&source=gbs_api\",\n" +
            "        \"infoLink\": \"http://books.google.co.in/books?id=M7ngCAAAQBAJ&dq=android&hl=&source=gbs_api\",\n" +
            "        \"canonicalVolumeLink\": \"https://books.google.com/books/about/Teach_Yourself_VISUALLY_Android_Phones_a.html?hl=&id=M7ngCAAAQBAJ\"\n" +
            "      },\n" +
            "      \"saleInfo\": {\n" +
            "        \"country\": \"IN\",\n" +
            "        \"saleability\": \"NOT_FOR_SALE\",\n" +
            "        \"isEbook\": false\n" +
            "      },\n" +
            "      \"accessInfo\": {\n" +
            "        \"country\": \"IN\",\n" +
            "        \"viewability\": \"PARTIAL\",\n" +
            "        \"embeddable\": true,\n" +
            "        \"publicDomain\": false,\n" +
            "        \"textToSpeechPermission\": \"ALLOWED\",\n" +
            "        \"epub\": {\n" +
            "          \"isAvailable\": false\n" +
            "        },\n" +
            "        \"pdf\": {\n" +
            "          \"isAvailable\": false\n" +
            "        },\n" +
            "        \"webReaderLink\": \"http://play.google.com/books/reader?id=M7ngCAAAQBAJ&hl=&printsec=frontcover&source=gbs_api\",\n" +
            "        \"accessViewStatus\": \"SAMPLE\",\n" +
            "        \"quoteSharingAllowed\": false\n" +
            "      },\n" +
            "      \"searchInfo\": {\n" +
            "        \"textSnippet\": \"This book is perfect for Android users at beginner to intermediate levels. The Android operating system is graphics intensive, which is why a visual guide is the best way to navigate your Android device.\"\n" +
            "      }\n" +
            "    },\n" +
            "    {\n" +
            "      \"kind\": \"books#volume\",\n" +
            "      \"id\": \"JGH0DwAAQBAJ\",\n" +
            "      \"etag\": \"gJl6U8C725s\",\n" +
            "      \"selfLink\": \"https://www.googleapis.com/books/v1/volumes/JGH0DwAAQBAJ\",\n" +
            "      \"volumeInfo\": {\n" +
            "        \"title\": \"Android For Dummies\",\n" +
            "        \"authors\": [\n" +
            "          \"Dan Gookin\"\n" +
            "        ],\n" +
            "        \"publisher\": \"John Wiley & Sons\",\n" +
            "        \"publishedDate\": \"2020-09-09\",\n" +
            "        \"description\": \"Your comprehensive (and very friendly!) reference guide to Android phones and tablets You’re used to hearing it said that the phone in your pocket or tablet by your bed has more computing power than the entire Apollo 11 space program in the 1960s (or something similarly impressive)—and this is no less true for Android devices than any other. Sounds great—but what does that actually mean you can do with them? The new edition of Android For Dummies reveals all for new and experienced users alike, making it easy to get the most out of the awesome computing power of Android smartphone and tablet devices—from communications and pictures and videos to the wonderful world of 2.8+ million Google apps! Cutting through the jargon, bestselling tech author Dan Gookin puts you in touch with all the Android features you’ll need to know (and many more you’ll be pleased to discover!), from setup and configuration to the major features, such as text, email, internet, maps, navigation, camera, and video, as well as synching with your home computer. In addition to getting familiar with these and the latest Android 10 operating system (OS)—in both Google Pixel and Samsung versions—you’ll become an expert on the best ways to share your thoughts, videos, and pictures on social media, navigate with Android Auto when driving, and maintain your files so they’re orderly and easy to find. Explore Android devices, from physical functions to software and online features Communicate via email, social media, Google Duo video calls, and more Tweak your privacy settings to keep your information secure Use Android Auto when driving and see in the dark with Night Light and Dark Mode Androids may be able to land a spacecraft on the Moon (yet) but there’s a whole universe waiting right there in the device at your fingertips—and this book is the perfect place to begin to explore!\",\n" +
            "        \"industryIdentifiers\": [\n" +
            "          {\n" +
            "            \"type\": \"ISBN_13\",\n" +
            "            \"identifier\": \"9781119711353\"\n" +
            "          },\n" +
            "          {\n" +
            "            \"type\": \"ISBN_10\",\n" +
            "            \"identifier\": \"1119711355\"\n" +
            "          }\n" +
            "        ],\n" +
            "        \"readingModes\": {\n" +
            "          \"text\": false,\n" +
            "          \"image\": true\n" +
            "        },\n" +
            "        \"pageCount\": 368,\n" +
            "        \"printType\": \"BOOK\",\n" +
            "        \"categories\": [\n" +
            "          \"Computers\"\n" +
            "        ],\n" +
            "        \"maturityRating\": \"NOT_MATURE\",\n" +
            "        \"allowAnonLogging\": false,\n" +
            "        \"contentVersion\": \"0.3.0.0.preview.1\",\n" +
            "        \"panelizationSummary\": {\n" +
            "          \"containsEpubBubbles\": false,\n" +
            "          \"containsImageBubbles\": false\n" +
            "        },\n" +
            "        \"imageLinks\": {\n" +
            "          \"smallThumbnail\": \"http://books.google.com/books/content?id=JGH0DwAAQBAJ&printsec=frontcover&img=1&zoom=5&edge=curl&source=gbs_api\",\n" +
            "          \"thumbnail\": \"http://books.google.com/books/content?id=JGH0DwAAQBAJ&printsec=frontcover&img=1&zoom=1&edge=curl&source=gbs_api\"\n" +
            "        },\n" +
            "        \"language\": \"en\",\n" +
            "        \"previewLink\": \"http://books.google.co.in/books?id=JGH0DwAAQBAJ&printsec=frontcover&dq=android&hl=&cd=5&source=gbs_api\",\n" +
            "        \"infoLink\": \"http://books.google.co.in/books?id=JGH0DwAAQBAJ&dq=android&hl=&source=gbs_api\",\n" +
            "        \"canonicalVolumeLink\": \"https://books.google.com/books/about/Android_For_Dummies.html?hl=&id=JGH0DwAAQBAJ\"\n" +
            "      },\n" +
            "      \"saleInfo\": {\n" +
            "        \"country\": \"IN\",\n" +
            "        \"saleability\": \"NOT_FOR_SALE\",\n" +
            "        \"isEbook\": false\n" +
            "      },\n" +
            "      \"accessInfo\": {\n" +
            "        \"country\": \"IN\",\n" +
            "        \"viewability\": \"PARTIAL\",\n" +
            "        \"embeddable\": true,\n" +
            "        \"publicDomain\": false,\n" +
            "        \"textToSpeechPermission\": \"ALLOWED\",\n" +
            "        \"epub\": {\n" +
            "          \"isAvailable\": false\n" +
            "        },\n" +
            "        \"pdf\": {\n" +
            "          \"isAvailable\": false\n" +
            "        },\n" +
            "        \"webReaderLink\": \"http://play.google.com/books/reader?id=JGH0DwAAQBAJ&hl=&printsec=frontcover&source=gbs_api\",\n" +
            "        \"accessViewStatus\": \"SAMPLE\",\n" +
            "        \"quoteSharingAllowed\": false\n" +
            "      },\n" +
            "      \"searchInfo\": {\n" +
            "        \"textSnippet\": \"Your comprehensive (and very friendly!) reference guide to Android phones and tablets You’re used to hearing it said that the phone in your pocket or tablet by your bed has more computing power than the entire Apollo 11 space program in ...\"\n" +
            "      }\n" +
            "    }\n" +
            "  ]\n" +
            "}";

    /**
     * Create a private constructor because no one should ever create a {@link QueryUtils} object.
     * This class is only meant to hold static variables and methods, which can be accessed
     * directly from the class name QueryUtils (and an object instance of QueryUtils is not needed).
     */
    private QueryUtils() {
    }

    /**
     * Return a list of {@link ABook} objects that has been built up from
     * parsing a JSON response.
     */
    public static ArrayList<ABook> extractBooks() {

        // Create an empty ArrayList that we can start adding earthquakes to
        ArrayList<ABook> booksArrayList = new ArrayList<>();

        // Try to parse the SAMPLE_JSON_RESPONSE. If there's a problem with the way the JSON
        // is formatted, a JSONException exception object will be thrown.
        // Catch the exception so the app doesn't crash, and print the error message to the logs.
        try {

            // TODO: Parse the response given by the SAMPLE_JSON_RESPONSE string and
            // build up a list of Earthquake objects with the corresponding data.
            JSONObject baseJSONResponse = new JSONObject(SAMPLE_JSON_RESPONSE);
            JSONArray itemsArray = baseJSONResponse.getJSONArray("items");

            for(int i = 0; i < itemsArray.length(); i++){
                JSONObject allObjects = itemsArray.getJSONObject(i);
                //in volumeInfo
                JSONObject volumeInfo = allObjects.getJSONObject("volumeInfo");
                String title = volumeInfo.getString("title");
                String authorName = "";
                JSONArray authors = volumeInfo.getJSONArray("authors");
                for(int j = 0; j < authors.length(); j++){
                    authorName += authors.getString(j);
                }
                String language = volumeInfo.getString("language");
                String pageCount = volumeInfo.getString("pageCount");
                String publisher = volumeInfo.getString("publisher");
                //in salesInfo
                JSONObject salesInfo = allObjects.getJSONObject("saleInfo");
                String saleability = salesInfo.getString("saleability");
                if(saleability == "FOR_SALE") {
                    JSONObject listPrice = salesInfo.getJSONObject("listPrice");
                    int price = listPrice.getInt("amount");
                    String currencyCode = listPrice.getString("currencyCode");
                    ABook book = new ABook(title,authorName,language,pageCount,publisher,currencyCode,price);
                    booksArrayList.add(book);
                }
                else{
                    ABook book = new ABook(title,authorName,language,pageCount,publisher,
                            "NOT_FOR_SALE",0);
                    booksArrayList.add(book);
                }

            }

        } catch (JSONException e) {
            // If an error is thrown when executing any of the above statements in the "try" block,
            // catch the exception here, so the app doesn't crash. Print a log message
            // with the message from the exception.
            Log.e("QueryUtils", "Problem parsing the earthquake JSON results", e);
        }

        // Return the list of earthquakes
        return booksArrayList;
    }

}