package com.marcrobito.examen.pojos

data class HackersContent(val by:String, val descendants:Int?, val id:Int,
                          val kids:List<Int>, val score:Int?, val time:Int,
                          val title:String, val type:String, val url:String,
                          val parent:String?, val text:String?, val about:String?)