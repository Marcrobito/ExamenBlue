package com.marcrobito.examen.network

class NetworkManager {

    class NetworkManager {
        val environment = NetworkEnvironment.Production
    }

    enum class NetworkEnvironment{
        Staging(),
        QA(),
        Production()
    }
}