/*刷新表格*/
function refresh(){
    var table = $('.table').DataTable();
    table.ajax.reload(null,false);// 刷新表格数据，分页信息不会重置
}

/*时间转换*/
function date(data){
    if(data==null||data==""){
        return "";
    }
    var time = new Date(data);
    var y = time.getFullYear();//年
    var m = time.getMonth() + 1;//月
    if (m >= 0 && m <= 9) {
        m = "0" + m;
    }
    var d = time.getDate();//日
    if (d >= 0 && d <= 9) {
        d = "0" + d;
    }
    var h = time.getHours();//时
    if (h >= 0 && h <= 9) {
        h = "0" + h;
    }
    var mm = time.getMinutes();//分
    if (mm >= 0 && mm <= 9) {
        mm = "0" + mm;
    }
    return (y+"-"+m+"-"+d+" "+h+":"+mm);
}

/*时间转换2*/
function dateAll(data){
    if(data==null||data==""){
        return "";
    }
    var time = new Date(data);
    var y = time.getFullYear();//年
    var m = time.getMonth() + 1;//月
    if (m >= 0 && m <= 9) {
        m = "0" + m;
    }
    var d = time.getDate();//日
    if (d >= 0 && d <= 9) {
        d = "0" + d;
    }
    var h = time.getHours();//时
    if (h >= 0 && h <= 9) {
        h = "0" + h;
    }
    var mm = time.getMinutes();//分
    if (mm >= 0 && mm <= 9) {
        mm = "0" + mm;
    }
    var ss = time.getSeconds();//秒
    if (ss >= 0 && ss <= 9) {
        ss = "0" + ss;
    }
    return (y+"-"+m+"-"+d+" "+h+":"+mm+":"+ss);
}

function formatPrice(val) {
    if(typeof val === 'string'){
        if(isNaN(val)){
            return null;
        }
        // 价格转为整数
        const index = val.lastIndexOf(".");
        let p = "";
        if(index < 0){
            // 无小数
            p = val + "00";
        }else if(index === p.length - 2){
            // 1位小数
            p = val.replace("\.","") + "0";
        }else{
            // 2位小数
            p = val.replace("\.","")
        }
        return parseInt(p);
    }else if(typeof val === 'number'){
        if(val == null){
            return null;
        }
        const s = val + '';
        if(s.length === 0){
            return "0.00";
        }
        if(s.length === 1){
            return "0.0" + val;
        }
        if(s.length === 2){
            return "0." + val;
        }
        const i = s.indexOf(".");
        if(i < 0){
            return s.substring(0, s.length - 2) + "." + s.substring(s.length-2)
        }
        const num = s.substring(0,i) + s.substring(i+1);
        if(i === 1){
            // 1位整数
            return "0.0" + num;
        }
        if(i === 2){
            return "0." + num;
        }
        if( i > 2){
            return num.substring(0,i-2) + "." + num.substring(i-2)
        }
    }
}

var baseurl ="http://api.anxinpha.com/api";

const parse = function (str, opts) {
    var options = opts ? utils.assign({}, opts) : {};

    if (options.decoder !== null && options.decoder !== undefined && typeof options.decoder !== 'function') {
        throw new TypeError('Decoder has to be a function.');
    }

    options.ignoreQueryPrefix = options.ignoreQueryPrefix === true;
    options.delimiter = typeof options.delimiter === 'string' || utils.isRegExp(options.delimiter) ? options.delimiter : defaults.delimiter;
    options.depth = typeof options.depth === 'number' ? options.depth : defaults.depth;
    options.arrayLimit = typeof options.arrayLimit === 'number' ? options.arrayLimit : defaults.arrayLimit;
    options.parseArrays = options.parseArrays !== false;
    options.decoder = typeof options.decoder === 'function' ? options.decoder : defaults.decoder;
    options.allowDots = typeof options.allowDots === 'boolean' ? options.allowDots : defaults.allowDots;
    options.plainObjects = typeof options.plainObjects === 'boolean' ? options.plainObjects : defaults.plainObjects;
    options.allowPrototypes = typeof options.allowPrototypes === 'boolean' ? options.allowPrototypes : defaults.allowPrototypes;
    options.parameterLimit = typeof options.parameterLimit === 'number' ? options.parameterLimit : defaults.parameterLimit;
    options.strictNullHandling = typeof options.strictNullHandling === 'boolean' ? options.strictNullHandling : defaults.strictNullHandling;

    if (str === '' || str === null || typeof str === 'undefined') {
        return options.plainObjects ? Object.create(null) : {};
    }

    var tempObj = typeof str === 'string' ? parseValues(str, options) : str;
    var obj = options.plainObjects ? Object.create(null) : {};

    // Iterate over the keys and setup the new object

    var keys = Object.keys(tempObj);
    for (var i = 0; i < keys.length; ++i) {
        var key = keys[i];
        var newObj = parseKeys(key, tempObj[key], options);
        obj = utils.merge(obj, newObj, options);
    }

    return utils.compact(obj);
};
const stringify = function(object, options) {
    let option =  {
        prefix : "",
        generateArrayPrefix : utils.generateArrayPrefix,
        strictNullHandling: null,
        skipNulls: null,
        encoder : utils.encode,
        filter: null,
        sort: null,
        allowDots : true,
        serializeDate: null,
        formatter : utils.formatter,
        encodeValuesOnly: true
    }
    Object.assign(option, options);
    let {prefix, generateArrayPrefix, strictNullHandling, skipNulls, encoder, filter,
        sort, allowDots, serializeDate, formatter, encodeValuesOnly} = option;

    var obj = object;
    if (typeof filter === 'function') {
        obj = filter(prefix, obj);
    } else if (obj instanceof Date) {
        obj = serializeDate(obj);
    } else if (obj === null) {
        obj = '';
    }
    var values = [];

    if (!obj) {
        return values;
    }

    if (typeof obj === 'string' || typeof obj === 'number' || typeof obj === 'boolean' || utils.isBuffer(obj)) {
        if (encoder) {
            var keyValue = encodeValuesOnly ? prefix : encoder(prefix, utils.encoder);
            if(allowDots){
                keyValue = keyValue.substring(1);
            }else{
                const arr =keyValue.match(/\[\w+\]/g);
                keyValue = arr[0].substring(1,arr[0].length-1) + keyValue.substring(arr[0].length);
            }
            return [keyValue + '=' + formatter(encoder(obj, utils.encoder))];
        }
        return [formatter(prefix) + '=' + formatter(String(obj))];
    }


    var objKeys;
    if (Array.isArray(filter)) {
        objKeys = filter;
    } else {
        var keys = Object.keys(obj);
        objKeys = sort ? keys.sort(sort) : keys;
    }

    for (var i = 0; i < objKeys.length; ++i) {
        var key = objKeys[i];

        if (skipNulls && obj[key] === null) {
            continue;
        }

        if (Array.isArray(obj)) {
            values = values.concat(this.stringify(
                obj[key],
                {prefix:generateArrayPrefix(prefix, key),
                    generateArrayPrefix,
                    strictNullHandling,
                    skipNulls,
                    encoder,
                    filter,
                    sort,
                    allowDots,
                    serializeDate,
                    formatter,
                    encodeValuesOnly}
            ));
        } else {
            values = values.concat(this.stringify(
                obj[key],
                {prefix:prefix + (allowDots ? '.' + key : '[' + key + ']'),
                    generateArrayPrefix,
                    strictNullHandling,
                    skipNulls,
                    encoder,
                    filter,
                    sort,
                    allowDots,
                    serializeDate,
                    formatter,
                    encodeValuesOnly}
            ));
        }
    }

    return values.join("&");
}

// 配置对象
const anxin = anxinpha = {
    /**
     * 对encodeURI()编码过的 URI 进行解码。并且获取其中的指定参数
     * @param name
     * @returns {*}
     */
    getUrlParam(name) {
        var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");
        var r = window.location.search.substr(1).match(reg);
        if (r != null) {
            return decodeURI(r[2]);
        }
        return "";
    },
    /**
     * 发起ajax请求工具，底层依然是axios
     */
    store: {
        set(key, value) {
            localStorage.setItem(key, JSON.stringify(value));
        },
        get(key) {
            return JSON.parse(localStorage.getItem(key));
        },
        del(key) {
            return localStorage.removeItem(key);
        }
    },
    /**
     * 将整数价格变为小数
     * @param val
     * @returns {*}
     */

    /**
     * 将日期格式化为指定格式
     * @param val
     * @param pattern
     * @returns {null}
     */
    formatDate(val, pattern) {
        if (!val) {
            return null;
        }
        if (!pattern) {
            pattern = "yyyy-MM-dd hh:mm:ss"
        }
        return new Date(val).format(pattern);
    },
    /**
     * 将js对象格式化为字符串参数对
     * @param object
     * @returns {*}
     */
    stringify,
    /**
     * 将请求参数字符串格式化为js对象
     */
    parse,
}
