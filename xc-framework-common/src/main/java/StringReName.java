import java.io.File;



    public class StringReName {
        public static void main(String[] args) {
            File file = new File("H:\\5.3微服务项目");
            cycle(file);
//        File[] files = file.listFiles();
//        for (File ff:files
//             ) {
//                    String string = ff.getName().substring(13);
//                    int a =0;
//                    while(string.charAt(a)=='_'){
//                        a++;
//                    }
//                    String newString = string.substring(a);
//
//                    String name = 54+"."+ff.getName().substring(0,2)+ "."+newString;
//                    System.out.println(name);
//                    ff.renameTo(new File(file,name));
//                }
        }
        static public void cycle(File file){
            File[] files = file.listFiles();
            for (File f:files
            ) {if(!f.isDirectory()){
                if(f.getName().endsWith(".avi")){

                    String name = f.getName();
                    int index = Integer.valueOf(f.getParentFile().getParentFile().getName().substring(3,5));
                    String newName = 60+index+"."+name.substring(0,2)+"."+name.substring(3);
                    System.out.println(newName);
                    f.renameTo(new File(f.getParentFile(),newName));
                }

            }else{
                cycle(f);
            }
            }


        }



    }


