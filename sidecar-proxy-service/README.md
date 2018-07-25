# Sidecar Proxy Server
1. 比如公网Nginx反向代理了多台服务server，不能直接访问服务server，却可以访问Nginx
2. 每个Nginx当做一个微服务，客户端侧负载均衡访问Nginx节点，Nginx侧服务端负载均衡访问服务server;
3. Nginx配置
   # health check
   location /health.json {
     default_type application/json;
     return 200 '{"status":"UP"}';
   }
   location /actuator/info {
        default_type application/json;
        return 200 '{}';
   }
   location /hello {
        default_type application/json;
        return 200 '{"code":200, "model": "hello world"}';
   }
