package fh_campuswien.banking_applications.accounts.audit;

import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component("auditAwareImpl")
public class auditAwareImpl implements AuditorAware {

    @Override
    public Optional getCurrentAuditor() {
        return Optional.of("ACCOUNT_MS");
    }
}
