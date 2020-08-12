package com.example.jkapplication.model

data class Monster(
    var title: String,
    var date: String,
    var img_url: String,
    var width: Int,
    var height: Int
)

// 입력받은 숫자의 리스트생성
fun createContactsList(numContacts: Int): ArrayList<Monster> {
    var contacts = ArrayList<Monster>();

    for (i in 1..numContacts) {
        contacts.add(
            Monster(
                title = "DemoTitle",
                date = "2020-07-23",
                img_url = "demo",
                width = 200,
                height = 200
            )
        );
    }

    return contacts;
}

fun getProgressItem(): Monster {
    return Monster(
        "progress",
        "progress",
        "progress",
        1,
        1
    )
}
