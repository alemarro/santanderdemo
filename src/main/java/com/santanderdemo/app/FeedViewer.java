package com.santanderdemo.app;

/** Interface with Spring services
 * Rest controller that finds the latest feed and channels to service
 * 
@RestController
public class FeedController {
    @Autowired
    private ISubscriber sub;

    @GetMapping(value = "/feed")
    public Feed getLatestFeed() {
        // Find latest feed
        return sub.findLatest();
    }
}


*/