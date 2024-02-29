# spring-security-autoconfig

This repo showcases how Spring Security's auto-configuration works. It has three sub-projects, which all work the same
way: there is a user with credentials: `user` / `password`. All three projects have tests, some of which pass and some
of which do not, to showcase the differences.

**1. no-auth-provider**

Runs on port 8080.

This is the baseline. When a `UserDetailsService` bean is provided, Spring
Security's `InitializeUserDetailsBeanManagerConfigurer` is used, and the appropriate `DaoAuthenticationProvider` is
wired in the global `AuthenticationManager`. Users can log in with `user` / `password`.

Suggested changes:

- add a log line to `InitializeUserDetailsBeanManagerConfigurer`, at the `INFO` or `DEBUG` level, notifying the user
  which `UserDetailsService` is being used.
- When there are multiple `UserDetailsService` beans, add a `WARN` log notifying the user that they are not
  auto-configured / used.

**2. single-auth-provider**

Runs on port 8081.

When a single `AuthenticationProvider` bean is provided, in addition to `UserDetailsService`,
the `InitializeAuthenticationProviderBeanManagerConfigurer` takes precedence
over `InitializeUserDetailsBeanManagerConfigurer`. The `AuthenticationProvider` bean _is_ invoked
on `AuthenticationManager#authenticate` and produces a log line. However, the users cannot log in.

Suggested changes:

- add a log line to `InitializeUserDetailsBeanManagerConfigurer`, at the `WARN` level, notifying the user that
  the `UserDetailsService` is ignored. If no `UserDetailsService` is available, do not issue a `WARN` log.
- add a log line to `InitializeAuthenticationProviderBeanManagerConfigurer`, at the `INFO` or `DEBUG` level, notifying
  the user which `AuthenticationProvider is being used.

**3. multiple-auth-providers**

Runs on port 8082.

When multiple `AuthenticationProvider` beans are provided (here, two beans),
the `InitializeAuthenticationProviderBeanManagerConfigurer` is not triggered. The `AuthenticationProvider`s are never
used and do not produce a log line.

Since there is a `UserDetailsService`, the `InitializeUserDetailsBeanManagerConfigurer` is triggered. Users can log in
with `user` / `password`.

Suggested changes:

- add a log line to `InitializeAuthenticationProviderBeanManagerConfigurer`, at the `WARN` level, notifying the user
  that the `AuthenticationProvider` beans, with their names, are ignored.