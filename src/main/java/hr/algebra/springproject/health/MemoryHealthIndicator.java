package hr.algebra.springproject.health;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

@Component
public class MemoryHealthIndicator implements HealthIndicator {
    @Override
    public Health health() {
        final long freeMemory = Runtime.getRuntime().freeMemory();
        final long totalMemory = Runtime.getRuntime().totalMemory();
        final double freeMemoryPercentage = ((double) freeMemory / (double) totalMemory) * 100;

        if (freeMemoryPercentage < 15) {
            return Health.down()
                    .withDetail("free_memory", freeMemory + " bytes")
                    .withDetail("total_memory", totalMemory + " bytes")
                    .withDetail("free_memory_percentage", freeMemoryPercentage + "%")
                    .build();
        }
        return Health.up()
                .withDetail("free_memory", freeMemory + " bytes")
                .withDetail("total_memory", totalMemory + " bytes")
                .withDetail("free_memory_percentage", freeMemoryPercentage + "%")
                .build();
    }
}
