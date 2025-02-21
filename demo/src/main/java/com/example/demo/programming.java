package com.example.demo;

import java.time.ZonedDateTime;

public class programming {
    public static void main(String[] args) {

        String ans="1";
        int n=4;
        for(int i=2;i<=n;i++){
            StringBuilder sb=new StringBuilder();
            int k=1;
            for(int j=1;j<ans.length();j++){
                if(ans.charAt(j)!=ans.charAt(j-1)){
                    sb.append(k).append(ans.charAt(j-1));
                    k=1;
                } else {k++;}
            }
            sb.append(k).append(ans.charAt(ans.length()-1));
            ans=sb.toString();
        }
        System.out.println(ans);
    }
}


