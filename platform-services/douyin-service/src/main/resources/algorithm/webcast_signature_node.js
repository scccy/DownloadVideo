const md5 = require('crypto').createHash('md5');
const fs = require('fs');
const path = require('path');

// 设置全局变量
global._window = global;
global._document = {};
global._navigator = {
    userAgent: "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/130.0.0.0 Safari/537.36 Edg/130.0.0.0"
};
global._history = {};
global._screen = {};
global._location = {};

// 加载原始签名生成脚本
const signatureScript = fs.readFileSync(path.join(__dirname, 'webcast_signature.js'), 'utf8');
eval(signatureScript);

// 将签名函数添加到全局作用域
global.get_signature = function(xMsStub) {
    return window.byted_acrawler.frontierSign({
        "X-MS-STUB": xMsStub
    });
};

// 接收命令行参数
const args = process.argv.slice(2);
const xMsStub = args[0];
const signatureHandler = args[1] || 'get_signature';

// 检查函数是否存在
if (typeof global[signatureHandler] !== 'function') {
    console.error(`Signature handler '${signatureHandler}' not found`);
    process.exit(1);
}

// 生成签名
const result = global[signatureHandler](xMsStub);
if (result && result['X-Bogus']) {
    console.log(result['X-Bogus']);
} else {
    console.error('Failed to generate signature');
    process.exit(1);
} 