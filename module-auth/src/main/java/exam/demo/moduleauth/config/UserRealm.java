package exam.demo.moduleauth.config;


import exam.demo.moduleauth.dao.UserDao;
import exam.demo.moduleauth.exception.AuthError;
import exam.demo.moduleauth.exception.AuthException;
import exam.demo.moduleauth.pojo.model.Role;
import exam.demo.moduleauth.pojo.model.User;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.ByteSource;

import javax.annotation.Resource;
import java.sql.Time;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Slf4j
public class UserRealm extends AuthorizingRealm {

    @Resource
    private UserDao userDao;

    /**
     * 授权
     **/
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        log.info("执行授权");
        Subject subject = SecurityUtils.getSubject();
        User user = (User)subject.getPrincipal();
        if(user != null){
            SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
            // 角色与权限字符串集合
            Collection<String> rolesCollection = new HashSet<>();
            Collection<String> premissionCollection = new HashSet<>();

            Set<Role> roles = user.getRoles();
            for(Role role : roles){
                rolesCollection.add(role.getName());
                Set<exam.demo.moduleauth.pojo.model.Resource> resources = role.getResources();
                for (exam.demo.moduleauth.pojo.model.Resource resource : resources){
                    premissionCollection.add(resource.getUrl());
                }
                info.addStringPermissions(premissionCollection);
            }
            info.addRoles(rolesCollection);
            return info;
        }
        return null;
    }

    /**
     * 认证
     **/
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {

        System.out.println("执行认证");
        long timeStamp = System.currentTimeMillis();
        UsernamePasswordToken token = (UsernamePasswordToken)authenticationToken;
        System.out.println("1 : " + (System.currentTimeMillis() - timeStamp));
        timeStamp = System.currentTimeMillis();
        User user = userDao.findByCode(token.getUsername());
        System.out.println("2 : " + (System.currentTimeMillis() - timeStamp));
        if (user == null){
            throw new AuthException(AuthError.USER_NOT_EXIST);
        }
        timeStamp = System.currentTimeMillis();
        ByteSource credentialsSalt = ByteSource.Util.bytes(user.getName());
        System.out.println("3 : "+(System.currentTimeMillis() - timeStamp));
        return new SimpleAuthenticationInfo(user, user.getPassword(), credentialsSalt, getName());
    }

    public static void main(String[] args){
        String hashAlgorithName = "MD5";
        String password = "123456";
        //加密次数
        int hashIterations = 32;

        ByteSource credentialsSalt = ByteSource.Util.bytes("肖又铭");
        Object obj = new SimpleHash(hashAlgorithName, password, credentialsSalt, hashIterations);
        System.out.println(obj);
    }
}
