package com.example.jkapplication.model

data class Monster(
    var title: String,
    var date: String,
    var img_url: String,
    var width: Int,
    var height:Int
)

// 입력받은 숫자의 리스트생성
fun createContactsList(numContacts: Int): ArrayList<Monster> {
    var contacts = ArrayList<Monster>();

    for (i in 1..numContacts) {
        contacts.add(
            Monster(
                title = "DemoTitle",
                date = "2020-07-23",
                img_url = "http://mblogthumb3.phinf.naver.net/20151231_162/jhsohn94_1451566021941xH4x5_JPEG/b0034380_11264094.jpg?type=w2",
                width = 200,
                height = 200
            )
        );
    }

    return contacts;
}


