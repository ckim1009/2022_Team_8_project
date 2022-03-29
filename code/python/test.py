import sys,os,re

i = 0
while i<1:
    i=i+1


temp = os.path.isfile(sys.argv[1])
if temp :


    f = open(sys.argv[1],"r", encoding='UTF8')
    line = f.read();
    special = re.compile(r'[^ A-Za-z0-9ㄱ-ㅎ가-힣,.\`\~\"\'\:\;\?\!@#\$%\^\&\*\-\+()\[\]\{\}+]')
    result = special.sub(' ',line)
    print(result);
    f.close();
else : 
    print("파일이 없습니다.")

exit();

