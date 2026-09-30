# Notes

## Authorization

Spring Security supports authorization at both the HTTP request level and the method level.

### HTTP Request Authorization

Authorization can be configured directly in `SecurityFilterChain`:

```java
.authorizeHttpRequests((requests) -> requests
    .requestMatchers("/", "/home").permitAll()
    .requestMatchers(HttpMethod.DELETE, "/items/*").hasRole("ADMIN")
    .anyRequest().authenticated()
)
```

This does not require `@EnableMethodSecurity`.

### Method-Level Authorization

Authorization can instead be applied directly to a controller or service method using `@PreAuthorize`:

```java
@PreAuthorize("hasRole('ADMIN')")
@DeleteMapping("/items/{id}")
public String deleteItem(@PathVariable Long id) {
    // ...
}
```

For `@PreAuthorize` to work, enable method security on the security configuration:

```java
@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class WebSecurityConfig {
    // ...
}
```

Method-level authorization is useful when the authorization rule belongs to the operation itself rather than a particular HTTP endpoint.

## `@EnableWebSecurity` and `@EnableMethodSecurity`

`@EnableWebSecurity` enables Spring Security's web security configuration, e.g. `SecurityFilterChain`.

`@EnableMethodSecurity` enables annotations such as `@PreAuthorize("hasRole('ADMIN')")` on controller/service methods.

If you're only using `.requestMatchers(...).hasRole(...)` in your `SecurityFilterChain`, you don't need `@EnableMethodSecurity`.
