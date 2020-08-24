///*
//package com.example.springbootshirodemo.jwt;
//
//import io.jsonwebtoken.Claims;
//import io.jsonwebtoken.JwtBuilder;
//import io.jsonwebtoken.Jwts;
//import io.jsonwebtoken.SignatureAlgorithm;
//
//import java.util.Date;
//import java.util.HashMap;
//import java.util.Map;
//import java.util.UUID;
//
//public class JwtUtils {
//    */
///**
//     * @param expireMillis     JWT的过期时间
//     * @param user 实体类
//     * @param key 密钥
//     * @return 用户登录成功后生成的 JWT 使用的Hs256算法 私钥使用 用户密码
//     *//*
//
//    private String key = "privatekey";
//    public static String createJWT(long expireMillis, User user, String key) {
//        //指定签名的时候使用的签名算法，也就是header中的 算法
//        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
//        //生成JWT的时间
//        long createMillis = System.currentTimeMillis();
//        Date createTime = new Date(createMillis);
//
//        //创建payload的私有声明(根据特定的业务需要添加，如果要拿这个做验证,一般是需要和JWT的接受方提前沟通)
//        Map<String, Object> claims = new HashMap<String, Object>();
//        claims.put("id", user.getId());
//        claims.put("username", user.getName());
//        claims.put("password", user.getPassword());
//
//
//        //生成签名是使用的密钥 secret ，这个方法本地封装的
//        // 一般可以从本地配置文件中读取，切记这个密钥不能不能外露 一旦客户端知道 密钥意味着 客户端可以自己签发密钥
////        String key = userEntity.getUserPassword();
//
//        //生成签发人
//        String subject = user.getName();
//
//        //下面就是在为payload添加各种标准声明和私有声明了
//        //这里其实就是new一个JWTBuild，设置jwt的body
//        JwtBuilder jwtBuilder = Jwts.builder()
//                //插入 私有声明，如果有私有声明，一定要先设置这个自己创建的私有的声明，这个是给builder的claim赋值，一旦写在标准的声明赋值之后，就是覆盖了那些标准的声明的
//                .setClaims(claims)
//                //设置JWT的唯一标识
//                .setId(UUID.randomUUID().toString())
//                //设置 创建时间，签发时间
//                .setIssuedAt(createTime)
//                //设置 唯一所有人 可以存放主键之类的不重复字段作为唯一标识，这个是一个json格式的字符串，可以存放什么userid，roldid之类的，作为什么用户的唯一标志。
//                .setSubject(subject)
//                //设置算法 和 密钥
//                .signWith(signatureAlgorithm, key);
//        //判断过期时间 如果大于0 则设置过期时间
//        if (expireMillis >= 0) {
//            long expMillis = createMillis + expireMillis;
//            Date exp = new Date(expMillis);
//            //设置过期时间
//            jwtBuilder.setExpiration(exp);
//        }
//        return jwtBuilder.compact();
//    }
//
//    */
///**
//     * Token解析
//     * @param token 被解析 JWT
//     * @param user
//     * @param key 用户实体对象 可以通过数据库查询
//     * @return 返回payload中存的数据
//     *//*
//
//    public static String  parseJWT(String token, User user, String key){
//
//        Claims claims = Jwts.parser()
//                //设置 密钥
//                .setSigningKey(key)
//                //设置需要解析的 token
//                .parseClaimsJws(token).getBody();
//        return claims.get("username").toString();
//    }
//
//    */
///**
//     * 验证 token信息 是否正确
//     * @param token 被解析 JWT
//     * @param key 密钥
//     * @return 是否正确
//     *//*
//
//    public static Boolean verify(String token,String key,User user){
//
//        try{
//            Jwts.parser()
//                    //设置 密钥
//                    .setSigningKey(key)
//                    //设置需要解析的 token
//                    .parseClaimsJws(token).getBody();
//            return true;
//        }catch (Exception e){
//            return false;
//        }
//
//    }
//}
//*/
