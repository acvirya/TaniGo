package com.tanigo.app.repository

import com.tanigo.app.R
import com.tanigo.app.model.Product

object ProductRepository {

    fun getAllProducts(): List<Product> {
        return listOf(
            Product(
                id = 1,
                name = "Traktor Roda 4",
                description = "Traktor roda 4 bertenaga 80 HP, cocok untuk lahan luas.",
                price = 120_000_000,
                imageRes = R.drawable.tractor
            ),
            Product(
                id = 2,
                name = "Mesin Bajak Sawah",
                description = "Mesin bajak sawah cocok untuk lahan basah",
                price = 80_000_000,
                imageRes = R.drawable.mesin_bajak
            ),
            Product(
                id = 3,
                name = "Mesin Panen Padi",
                description = "Mesin modern untuk panen padi lebih cepat",
                price = 200_000_000,
                imageRes = R.drawable.harvester
            )
        )
    }
}
