let documentSuffix =[
  'ppt','pptx','pptm','ppsx','ppsm','pps','potx','potm','dpt','dps',      //演示文件
  'et','xls','xlt','xlsx','xlsm','xltx','xltm','csv',                     //表格文件
  'doc','docx','txt','dot','wps','wpt','dotx','docm','dotm',              //文字文件
  'pdf',                                                                  //pdf
]
export  function checkDocumentType(suffix){
  if ('' == suffix || undefined==suffix){
    return false
  }
  if (documentSuffix.indexOf(suffix)>-1){
    return true
  }else {
    return false
  }
}
