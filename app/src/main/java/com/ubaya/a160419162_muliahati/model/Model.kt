package com.ubaya.a160419162_muliahati.model

import java.math.BigInteger


data class Profile(var fullName: String?,
                   var email: String?,
                   var date_of_birth: String?,
                   var bio: String?,
                   var donasiku: Int?,
                   var photo_profile: String?)

data class Donasi(var id: String?,
                  var judulDonasi: String?,
                  var target: Int?,
                  var terkumpul: BigInteger,
                  var donatur: Int?,
                  var durasi: Int?,
                  var kategori: String?,
                  var penggalang: Penggalang,
                  var foto_donasi: String?,
                  var cerita: String?
)
data class Zakat(var id: String?,
                  var judulDonasi: String?,
                  var target: Int?,
                  var terkumpul: BigInteger,
                  var donatur: Int?,
                  var durasi: Int?,
                  var kategori: String?,
                  var penggalang: Penggalang,
                  var foto_donasi: String?,
                  var cerita: String?
)
data class Penggalang(var nama: String?,
                      var tentang: String?,
                      var foto_penggalang: String?)