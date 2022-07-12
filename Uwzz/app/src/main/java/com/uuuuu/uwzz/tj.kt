package com.uuuuu.uwzz

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.uuuuu.uwzz.databinding.ActivityTjtBinding
import java.util.ArrayList
import java.util.HashMap
import java.util.regex.Pattern

class tj : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        val binding = ActivityTjtBinding.inflate(layoutInflater)
        setContentView(binding.root)
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_tjt)
//        var textView:TextView= findViewById(R.id.tttttjjjjjj)
        val userName: MutableList<String?> = ArrayList<String?>() //姓名
        val avg: MutableList<String?> = ArrayList<String?>() //作业平均分
//        val completeNum: MutableList<String?> = ArrayList<String?>() //作业总数
        val workSubmited: MutableList<String?> = ArrayList<String?>() //作业提交数
        val workMarked: MutableList<String?> = ArrayList<String?>() //作业批阅数
        val workmax: MutableList<String?> = ArrayList<String?>()//作业最高分
        val workmin: MutableList<String?> = ArrayList<String?>() //作业最低分
        //        String s = "{\"total\":68,\"data\":[{\"completeNum\":6,\"aliasName\":\"20195103035\",\"avg\":\"100\",\"min\":\"100\",\"max\":\"100\",\"personId\":92858392,\"workSubmited\":5,\"workMarked\":4,\"userName\":\"钟芳怡\"},{\"completeNum\":6,\"aliasName\":\"20193115036\",\"avg\":\"95.7\",\"min\":\"90\",\"max\":\"100\",\"personId\":92860076,\"workSubmited\":6,\"workMarked\":5,\"userName\":\"王绍启\"},{\"completeNum\":6,\"aliasName\":\"20191107216\",\"avg\":\"100\",\"min\":\"100\",\"max\":\"100\",\"personId\":92859167,\"workSubmited\":4,\"workMarked\":3,\"userName\":\"杜萍胜男\"},{\"completeNum\":6,\"aliasName\":\"20191107215\",\"avg\":\"100\",\"min\":\"100\",\"max\":\"100\",\"personId\":92859038,\"workSubmited\":5,\"workMarked\":4,\"userName\":\"袁苑\"},{\"completeNum\":6,\"aliasName\":\"20191107214\",\"avg\":\"100\",\"min\":\"100\",\"max\":\"100\",\"personId\":92859102,\"workSubmited\":4,\"workMarked\":3,\"userName\":\"雷思怡\"},{\"completeNum\":6,\"aliasName\":\"20191107213\",\"avg\":\"100\",\"min\":\"100\",\"max\":\"100\",\"personId\":92859099,\"workSubmited\":5,\"workMarked\":4,\"userName\":\"李子璐\"},{\"completeNum\":6,\"aliasName\":\"20191107212\",\"avg\":\"91.45\",\"min\":\"80\",\"max\":\"100\",\"personId\":92859051,\"workSubmited\":5,\"workMarked\":4,\"userName\":\"邵齐\"},{\"completeNum\":6,\"aliasName\":\"20191107211\",\"avg\":\"100\",\"min\":\"100\",\"max\":\"100\",\"personId\":92859055,\"workSubmited\":6,\"workMarked\":5,\"userName\":\"李琴\"},{\"completeNum\":6,\"aliasName\":\"20191107210\",\"avg\":\"84\",\"min\":\"66.2\",\"max\":\"100\",\"personId\":92859122,\"workSubmited\":4,\"workMarked\":3,\"userName\":\"张雨情\"},{\"completeNum\":6,\"aliasName\":\"20191107209\",\"avg\":\"100\",\"min\":\"100\",\"max\":\"100\",\"personId\":92859151,\"workSubmited\":5,\"workMarked\":4,\"userName\":\"郑怡丹\"},{\"completeNum\":6,\"aliasName\":\"20191107208\",\"avg\":\"100\",\"min\":\"100\",\"max\":\"100\",\"personId\":92859025,\"workSubmited\":1,\"workMarked\":1,\"userName\":\"汤有泰\"},{\"completeNum\":6,\"aliasName\":\"20191107207\",\"avg\":\"97\",\"min\":\"91\",\"max\":\"100\",\"personId\":92859165,\"workSubmited\":4,\"workMarked\":3,\"userName\":\"周瑞\"},{\"completeNum\":6,\"aliasName\":\"20191107206\",\"avg\":\"81.3\",\"min\":\"71.6\",\"max\":\"91\",\"personId\":92859160,\"workSubmited\":3,\"workMarked\":2,\"userName\":\"匡杨尚\"},{\"completeNum\":6,\"aliasName\":\"20191107205\",\"avg\":\"99.4\",\"min\":\"98.2\",\"max\":\"100\",\"personId\":92859008,\"workSubmited\":4,\"workMarked\":3,\"userName\":\"张成\"},{\"completeNum\":6,\"aliasName\":\"20191107204\",\"avg\":\"96.45\",\"min\":\"85.8\",\"max\":\"100\",\"personId\":92859107,\"workSubmited\":5,\"workMarked\":4,\"userName\":\"马一龙\"},{\"completeNum\":6,\"aliasName\":\"20191107201\",\"avg\":\"0\",\"min\":\"0\",\"max\":\"0\",\"personId\":92859166,\"workSubmited\":1,\"workMarked\":0,\"userName\":\"杨猛\"},{\"completeNum\":6,\"aliasName\":\"20191107199\",\"avg\":\"100\",\"min\":\"100\",\"max\":\"100\",\"personId\":92859105,\"workSubmited\":3,\"workMarked\":2,\"userName\":\"张航建\"},{\"completeNum\":6,\"aliasName\":\"20191107198\",\"avg\":\"100\",\"min\":\"100\",\"max\":\"100\",\"personId\":92859109,\"workSubmited\":6,\"workMarked\":5,\"userName\":\"谢权\"},{\"completeNum\":6,\"aliasName\":\"20191107197\",\"avg\":\"90.53\",\"min\":\"74.4\",\"max\":\"100\",\"personId\":92859147,\"workSubmited\":4,\"workMarked\":3,\"userName\":\"周义\"},{\"completeNum\":6,\"aliasName\":\"20191107196\",\"avg\":\"80.1\",\"min\":\"80\",\"max\":\"80.2\",\"personId\":92859070,\"workSubmited\":3,\"workMarked\":2,\"userName\":\"黄景天\"},{\"completeNum\":6,\"aliasName\":\"20191107195\",\"avg\":\"100\",\"min\":\"100\",\"max\":\"100\",\"personId\":92859116,\"workSubmited\":5,\"workMarked\":4,\"userName\":\"缪森\"},{\"completeNum\":6,\"aliasName\":\"20191107194\",\"avg\":\"100\",\"min\":\"100\",\"max\":\"100\",\"personId\":92859037,\"workSubmited\":6,\"workMarked\":5,\"userName\":\"黄一珉\"},{\"completeNum\":6,\"aliasName\":\"20191107192\",\"avg\":\"87\",\"min\":\"87\",\"max\":\"87\",\"personId\":92859129,\"workSubmited\":1,\"workMarked\":1,\"userName\":\"徐成杰\"},{\"completeNum\":6,\"aliasName\":\"20191107191\",\"avg\":\"93.32\",\"min\":\"73.3\",\"max\":\"100\",\"personId\":92859046,\"workSubmited\":5,\"workMarked\":4,\"userName\":\"温传宝\"},{\"completeNum\":6,\"aliasName\":\"20191107189\",\"avg\":\"100\",\"min\":\"100\",\"max\":\"100\",\"personId\":92859088,\"workSubmited\":5,\"workMarked\":4,\"userName\":\"朱龙志\"},{\"completeNum\":6,\"aliasName\":\"20191107187\",\"avg\":\"94.67\",\"min\":\"86.8\",\"max\":\"100\",\"personId\":92859027,\"workSubmited\":4,\"workMarked\":3,\"userName\":\"李宇航\"},{\"completeNum\":6,\"aliasName\":\"20191107186\",\"avg\":\"98.1\",\"min\":\"94.3\",\"max\":\"100\",\"personId\":92859155,\"workSubmited\":4,\"workMarked\":3,\"userName\":\"雷廷峰\"},{\"completeNum\":6,\"aliasName\":\"20191107185\",\"avg\":\"86.5\",\"min\":\"86.5\",\"max\":\"86.5\",\"personId\":92859173,\"workSubmited\":2,\"workMarked\":1,\"userName\":\"张祖勋\"},{\"completeNum\":6,\"aliasName\":\"20191107184\",\"avg\":\"77.97\",\"min\":\"60.2\",\"max\":\"100\",\"personId\":92859016,\"workSubmited\":4,\"workMarked\":4,\"userName\":\"李宇翔\"},{\"completeNum\":6,\"aliasName\":\"20191107183\",\"avg\":\"90.8\",\"min\":\"81\",\"max\":\"97.2\",\"personId\":92859020,\"workSubmited\":4,\"workMarked\":3,\"userName\":\"周海\"},{\"completeNum\":6,\"aliasName\":\"20191107182\",\"avg\":\"99.07\",\"min\":\"97.2\",\"max\":\"100\",\"personId\":92859000,\"workSubmited\":4,\"workMarked\":3,\"userName\":\"陆朋伟\"},{\"completeNum\":6,\"aliasName\":\"20191107171\",\"avg\":\"99.55\",\"min\":\"98.2\",\"max\":\"100\",\"personId\":92859098,\"workSubmited\":5,\"workMarked\":4,\"userName\":\"吴涵琪\"},{\"completeNum\":6,\"aliasName\":\"20191107170\",\"avg\":\"99.1\",\"min\":\"95.5\",\"max\":\"100\",\"personId\":92859032,\"workSubmited\":6,\"workMarked\":5,\"userName\":\"丁婷\"},{\"completeNum\":6,\"aliasName\":\"20191107169\",\"avg\":\"97.2\",\"min\":\"88.8\",\"max\":\"100\",\"personId\":92859139,\"workSubmited\":5,\"workMarked\":4,\"userName\":\"于宇\"},{\"completeNum\":6,\"aliasName\":\"20191107168\",\"avg\":\"92.27\",\"min\":\"85.8\",\"max\":\"100\",\"personId\":92859112,\"workSubmited\":4,\"workMarked\":3,\"userName\":\"欧阳晓\"},{\"completeNum\":6,\"aliasName\":\"20191107167\",\"avg\":\"100\",\"min\":\"100\",\"max\":\"100\",\"personId\":92859044,\"workSubmited\":4,\"workMarked\":3,\"userName\":\"王心怡\"},{\"completeNum\":6,\"aliasName\":\"20191107166\",\"avg\":\"98.84\",\"min\":\"94.2\",\"max\":\"100\",\"personId\":92859134,\"workSubmited\":6,\"workMarked\":5,\"userName\":\"林静怡\"},{\"completeNum\":6,\"aliasName\":\"20191107165\",\"avg\":\"91.7\",\"min\":\"81\",\"max\":\"100\",\"personId\":92859108,\"workSubmited\":5,\"workMarked\":4,\"userName\":\"周渝\"},{\"completeNum\":6,\"aliasName\":\"20191107164\",\"avg\":\"100\",\"min\":\"100\",\"max\":\"100\",\"personId\":92859024,\"workSubmited\":6,\"workMarked\":5,\"userName\":\"潘永婵\"},{\"completeNum\":6,\"aliasName\":\"20191107163\",\"avg\":\"80.35\",\"min\":\"78.7\",\"max\":\"82\",\"personId\":92859168,\"workSubmited\":3,\"workMarked\":2,\"userName\":\"代壹任\"},{\"completeNum\":6,\"aliasName\":\"20191107161\",\"avg\":\"100\",\"min\":\"100\",\"max\":\"100\",\"personId\":92859159,\"workSubmited\":5,\"workMarked\":4,\"userName\":\"王豪杰\"},{\"completeNum\":6,\"aliasName\":\"20191107160\",\"avg\":\"96.67\",\"min\":\"90\",\"max\":\"100\",\"personId\":92859163,\"workSubmited\":3,\"workMarked\":3,\"userName\":\"陈龙\"},{\"completeNum\":6,\"aliasName\":\"20191107159\",\"avg\":\"88.6\",\"min\":\"83\",\"max\":\"94.2\",\"personId\":92859149,\"workSubmited\":3,\"workMarked\":2,\"userName\":\"王艺博\"},{\"completeNum\":6,\"aliasName\":\"20191107156\",\"avg\":\"85.03\",\"min\":\"68.5\",\"max\":\"100\",\"personId\":92859148,\"workSubmited\":5,\"workMarked\":4,\"userName\":\"郑豪棋\"},{\"completeNum\":6,\"aliasName\":\"20191107155\",\"avg\":\"94.2\",\"min\":\"82.6\",\"max\":\"100\",\"personId\":92859054,\"workSubmited\":4,\"workMarked\":3,\"userName\":\"柯希虎\"},{\"completeNum\":6,\"aliasName\":\"20191107154\",\"avg\":\"97.75\",\"min\":\"91\",\"max\":\"100\",\"personId\":92859097,\"workSubmited\":5,\"workMarked\":4,\"userName\":\"严宇昊\"},{\"completeNum\":6,\"aliasName\":\"20191107153\",\"avg\":\"100\",\"min\":\"100\",\"max\":\"100\",\"personId\":92859073,\"workSubmited\":3,\"workMarked\":3,\"userName\":\"许陶然\"},{\"completeNum\":6,\"aliasName\":\"20191107152\",\"avg\":\"100\",\"min\":\"100\",\"max\":\"100\",\"personId\":92859140,\"workSubmited\":3,\"workMarked\":2,\"userName\":\"陈聪\"},{\"completeNum\":6,\"aliasName\":\"20191107151\",\"avg\":\"100\",\"min\":\"100\",\"max\":\"100\",\"personId\":92859146,\"workSubmited\":6,\"workMarked\":5,\"userName\":\"曾畅\"},{\"completeNum\":6,\"aliasName\":\"20191107150\",\"avg\":\"92.9\",\"min\":\"85.8\",\"max\":\"100\",\"personId\":92859077,\"workSubmited\":2,\"workMarked\":2,\"userName\":\"谢树恒\"},{\"completeNum\":6,\"aliasName\":\"20191107149\",\"avg\":\"94.6\",\"min\":\"89.2\",\"max\":\"100\",\"personId\":92859137,\"workSubmited\":2,\"workMarked\":2,\"userName\":\"姚鑫\"},{\"completeNum\":6,\"aliasName\":\"20191107148\",\"avg\":\"100\",\"min\":\"100\",\"max\":\"100\",\"personId\":92859057,\"workSubmited\":5,\"workMarked\":4,\"userName\":\"寇洋\"},{\"completeNum\":6,\"aliasName\":\"20191107147\",\"avg\":\"100\",\"min\":\"100\",\"max\":\"100\",\"personId\":92859125,\"workSubmited\":1,\"workMarked\":1,\"userName\":\"袁创\"},{\"completeNum\":6,\"aliasName\":\"20191107146\",\"avg\":\"90.8\",\"min\":\"64\",\"max\":\"100\",\"personId\":92859126,\"workSubmited\":6,\"workMarked\":5,\"userName\":\"周敏\"},{\"completeNum\":6,\"aliasName\":\"20191107145\",\"avg\":\"93.15\",\"min\":\"84.2\",\"max\":\"100\",\"personId\":92859132,\"workSubmited\":5,\"workMarked\":4,\"userName\":\"罗时豪\"},{\"completeNum\":6,\"aliasName\":\"20191107144\",\"avg\":\"97\",\"min\":\"91\",\"max\":\"100\",\"personId\":92859068,\"workSubmited\":4,\"workMarked\":3,\"userName\":\"周德涛\"},{\"completeNum\":6,\"aliasName\":\"20191107143\",\"avg\":\"100\",\"min\":\"100\",\"max\":\"100\",\"personId\":92859035,\"workSubmited\":2,\"workMarked\":2,\"userName\":\"熊葭萌\"},{\"completeNum\":6,\"aliasName\":\"20191107142\",\"avg\":\"100\",\"min\":\"100\",\"max\":\"100\",\"personId\":92859019,\"workSubmited\":1,\"workMarked\":1,\"userName\":\"谭棋琦\"},{\"completeNum\":6,\"aliasName\":\"20191107141\",\"avg\":\"96.75\",\"min\":\"92.8\",\"max\":\"100\",\"personId\":92859154,\"workSubmited\":5,\"workMarked\":4,\"userName\":\"陈天涵\"},{\"completeNum\":6,\"aliasName\":\"20191107140\",\"avg\":\"100\",\"min\":\"100\",\"max\":\"100\",\"personId\":92859172,\"workSubmited\":4,\"workMarked\":3,\"userName\":\"黄千禧\"},{\"completeNum\":6,\"aliasName\":\"20191107137\",\"avg\":\"88.03\",\"min\":\"64.1\",\"max\":\"100\",\"personId\":92859005,\"workSubmited\":4,\"workMarked\":3,\"userName\":\"聂家琪\"},{\"completeNum\":6,\"aliasName\":\"20191101258\",\"avg\":\"72.6\",\"min\":\"70\",\"max\":\"80.2\",\"personId\":92857832,\"workSubmited\":6,\"workMarked\":5,\"userName\":\"胡鸿成\"},{\"completeNum\":6,\"aliasName\":\"20181107190\",\"avg\":\"81.4\",\"min\":\"71.6\",\"max\":\"94.2\",\"personId\":81747200,\"workSubmited\":4,\"workMarked\":3,\"userName\":\"彭晓雯\"},{\"completeNum\":6,\"aliasName\":\"20181107177\",\"avg\":\"100\",\"min\":\"90\",\"max\":\"100\",\"personId\":81800014,\"workSubmited\":5,\"workMarked\":4,\"userName\":\"雷鹏\"},{\"completeNum\":6,\"aliasName\":\"20181107146\",\"avg\":\"86.6\",\"min\":\"80\",\"max\":\"97.2\",\"personId\":81767391,\"workSubmited\":3,\"workMarked\":3,\"userName\":\"韩越\"},{\"completeNum\":6,\"aliasName\":\"20181107130\",\"avg\":\"100\",\"min\":\"100\",\"max\":\"100\",\"personId\":101118818,\"workSubmited\":5,\"workMarked\":4,\"userName\":\"陈康辉\"},{\"completeNum\":6,\"aliasName\":\"20172101023\",\"avg\":\"100\",\"min\":\"100\",\"max\":\"100\",\"personId\":108861964,\"workSubmited\":2,\"workMarked\":2,\"userName\":\"喻琦傲\"},{\"completeNum\":6,\"aliasName\":\"20171110026\",\"avg\":\"90.43\",\"min\":\"61.7\",\"max\":\"100\",\"personId\":108865430,\"workSubmited\":5,\"workMarked\":4,\"userName\":\"赵正\"}],\"totalPage\":1,\"page\":1}";
        val mUserName = Pattern.compile("\"userName\":\"[\\u4E00-\\u9FA5]+\"").matcher(Uwc.a)
        val mAvg = Pattern.compile("\"avg\":\"[0-9|.]+\"").matcher(Uwc.a)
        //        Matcher mCompleteNum = Pattern.compile("\"completeNum\":[0-9]+").matcher(a);
        val mWorkSubmited = Pattern.compile("\"workSubmited\":[0-9]+").matcher(Uwc.a)
        val mWorkMarked = Pattern.compile("\"workMarked\":[0-9]+").matcher(Uwc.a)
        val mMax = Pattern.compile("\"max\":\"[0-9|.]+\"").matcher(Uwc.a)
        val mMin = Pattern.compile("\"min\":\"[0-9|.]+\"").matcher(Uwc.a)
        while (mUserName.find()) {
            userName.add(getChinese(mUserName.group()))
        }
        while (mAvg.find()) {
            avg.add(getFloatNum(mAvg.group()))
        }
        while (mMax.find()) {
            workmax.add(getFloatNum(mMax.group()))
        }
        while (mMin.find()) {
            workmin.add(getFloatNum(mMin.group()))
        }
        //        while (mCompleteNum.find()) {
//            completeNum.add(getIntNum(mCompleteNum.group()));
//        }
        while (mWorkSubmited.find()) {
            workSubmited.add(getIntNum(mWorkSubmited.group()))
        }
        while (mWorkMarked.find()) {
            workMarked.add(getIntNum(mWorkMarked.group()))
        }

        var m = HashMap<String, Double>()
        for (i in avg.indices) {
            avg.get(i)?.let {
                m.put(
                    "姓名：${userName[i]}\t提交数：${workSubmited[i]}\t批阅数：${workMarked[i]}\t平均分${avg[i]}\t最高分：${workmax[i]}\t最低分：${workmin[i]}\n",
                    it.toDouble()
                )
            };
        }
        var ms = m.entries.sortedBy { it.value }.associateBy({ it.key }, { it.value })
        for(vv in ms.keys.reversed()){
            binding.tttttjjjjjj.append(vv)
        }
    }

    companion object {
        fun getChinese(s: String?): String? {
            val rm = Pattern.compile("[\\u4E00-\\u9FA5]+").matcher(s)
            return if (rm.find()) rm.group() else null
        }

        fun getIntNum(s: String?): String? {
            val rm = Pattern.compile("[0-9]+").matcher(s)
            return if (rm.find()) rm.group() else null
        }

        fun getFloatNum(s: String?): String? {
            val rm = Pattern.compile("[0-9|.]+").matcher(s)
            return if (rm.find()) rm.group() else null
        }
    }
}