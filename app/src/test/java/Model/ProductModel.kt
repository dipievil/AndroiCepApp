package Model

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName


class Produto {
    @SerializedName("id")
    @Expose
    var id: Int? = null

    @SerializedName("name")
    @Expose
    var name: String? = null

    @SerializedName("preco")
    @Expose
    var preco: Int? = null
}