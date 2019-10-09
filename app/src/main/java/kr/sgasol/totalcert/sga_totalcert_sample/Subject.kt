
import java.util.*

class Subject{

    constructor(`class`: String, name: String, pro: String, index: List<Int>) {
        this.`class` = `class`
        this.name = name
        this.pro = pro
        this.index = index
    }

    constructor(){
        `class`=""
        name=""
        pro=""
        index= ArrayList<Int>()
    }


    var `class`:String;
    var name:String;
    var pro:String;
    var index:List<Int>;

}