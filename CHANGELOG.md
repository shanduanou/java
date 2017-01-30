
## [v4.4.1](https://github.com/pubnub/java/tree/v4.4.1)


  [Full Changelog](https://github.com/pubnub/java/compare/v4.4.0...v4.4.1)



- 🐛SDK did not honor the exhaustion of reconnections, it will now disconnect once max retries happened


## [v4.4.0](https://github.com/pubnub/java/tree/v4.4.0)


  [Full Changelog](https://github.com/pubnub/java/compare/v4.3.1...v4.4.0)


- ⭐Support for maximum reconnection attempts



- ⭐Populate affectedChannel and affectedChannelGroups



- ⭐Support for GAE



- ⭐Emit pnconnected when adding / removing channels.



## [v4.3.1](https://github.com/pubnub/java/tree/v4.3.1)


  [Full Changelog](https://github.com/pubnub/java/compare/v4.3.0...v4.3.1)


- ⭐support for key-level grant.



## [v4.3.0](https://github.com/pubnub/java/tree/v4.3.0)


  [Full Changelog](https://github.com/pubnub/java/compare/v4.2.3...v4.3.0)


- ⭐JSON parser is switched to GSON, new artifact on nexus as pubnub-gson



- ⭐GetState, setState return a JsonElement instead of a plain object.



## [v4.2.3](https://github.com/pubnub/java/tree/v4.2.3)


  [Full Changelog](https://github.com/pubnub/java/compare/v4.2.2...v4.2.3)


- ⭐Swapping out logger for slf4japi and removing final methods



## [v4.2.2](https://github.com/pubnub/java/tree/v4.2.2)


  [Full Changelog](https://github.com/pubnub/java/compare/v4.2.1...v4.2.2)


- ⭐remove final identifiers from the public facing API.



## [v4.2.1](https://github.com/pubnub/java/tree/v4.2.1)


  [Full Changelog](https://github.com/pubnub/java/compare/v4.2.0...v4.2.1)


- ⭐include publisher UUID on incoming message



- ⭐allow to set custom TTL on a publish



## [v4.2.0](https://github.com/pubnub/java/tree/v4.2.0)


  [Full Changelog](https://github.com/pubnub/java/compare/v4.1.0...v4.2.0)


- ⭐Signatures are generated for all requests with secret key to ensure secure transmission of data



- ⭐support for alerting of queue exceeded (PNRequestMessageCountExceededCategory)



- ⭐signaling to okhttp to stop the queues on termination.



## [v4.1.0](https://github.com/pubnub/java/tree/v4.1.0)


  [Full Changelog](https://github.com/pubnub/java/compare/v4.0.14...v4.1.0)


- ⭐destory now correctly forces the producer thread to shut down; stop is now deprecated for disconnect



- ⭐support for sending instance id for presence detection (disabled by default)



- ⭐support for sending request id to burst cache (enabled by default)



- ⭐proxy support via the native proxy configurator class.



## [v4.0.14](https://github.com/pubnub/java/tree/v4.0.14)


  [Full Changelog](https://github.com/pubnub/java/compare/v4.0.13...v4.0.14)


- ⭐on PAM error, populate the affectedChannel or affectedChannelGroup to signal which channels are failing



## [v4.0.13](https://github.com/pubnub/java/tree/v4.0.13)


  [Full Changelog](https://github.com/pubnub/java/compare/v4.0.12...v4.0.13)


- ⭐populate jso with the error.



## [v4.0.12](https://github.com/pubnub/java/tree/v4.0.12)


  [Full Changelog](https://github.com/pubnub/java/compare/v4.0.11...v4.0.12)



- 🐛fixing parsing of origination payload within the psv2 enevelope


## [v4.0.11](https://github.com/pubnub/java/tree/v4.0.11)


  [Full Changelog](https://github.com/pubnub/java/compare/v4.0.10...v4.0.11)


- ⭐bumping build process for gradle 3 / merging documentation into the repo and test adjustments



## [v4.0.10](https://github.com/pubnub/java/tree/v4.0.10)


  [Full Changelog](https://github.com/pubnub/java/compare/v4.0.9...v4.0.10)


- ⭐adding channel / channelGroup fields when a message / presence event comes in.



## [v4.0.9](https://github.com/pubnub/java/tree/v4.0.9)


  [Full Changelog](https://github.com/pubnub/java/compare/v4.0.8...v4.0.9)


- ⭐adjustments for handling pn_other and decryption



- ⭐retrofit version bumps.



## [v4.0.8](https://github.com/pubnub/java/tree/v4.0.8)


  [Full Changelog](https://github.com/pubnub/java/compare/v4.0.7...v4.0.8)

- 🌟added unsubscribeAll, getSubscribedChannels, getSubscribedChannelGroups



- 🌟SDK will establish secure connections by default



- 🌟added support for exponential backoff reconnection policies




## [v4.0.7](https://github.com/pubnub/java/tree/v4.0.7)


  [Full Changelog](https://github.com/pubnub/java/compare/v4.0.6...v4.0.7)


- ⭐reduce overlap on error handling when returning exceptions.



## [v4.0.6](https://github.com/pubnub/java/tree/v4.0.6)


  [Full Changelog](https://github.com/pubnub/java/compare/v4.0.5...v4.0.6)


- ⭐send heartbeat presence value when subscribing



## [v4.0.5](https://github.com/pubnub/java/tree/v4.0.5)


  [Full Changelog](https://github.com/pubnub/java/compare/v4.0.4...v4.0.5)


- ⭐unified retrofit handling to lower amount of instances and sync'd the state methods.



## [v4.0.4](https://github.com/pubnub/java/tree/v4.0.4)


  [Full Changelog](https://github.com/pubnub/java/compare/v4.0.3...v4.0.4)



- 🐛setting State for other UUID's is now supported.


## [v4.0.3](https://github.com/pubnub/java/tree/v4.0.3)


  [Full Changelog](https://github.com/pubnub/java/compare/v4.0.2...v4.0.3)

- 🌟fire() method and no-replicaton options.




## [v4.0.2](https://github.com/pubnub/java/tree/v4.0.2)


  [Full Changelog](https://github.com/pubnub/java/compare/v4.0.1...v4.0.2)



- 🐛fix to the version fetching.


## [v4.0.1](https://github.com/pubnub/java/tree/v4.0.1)


  [Full Changelog](https://github.com/pubnub/java/compare/v4.0.0...v4.0.1)



- 🐛adjustment of the subscribe loop to alleviate duplicate dispatches.


## [v4.0.0](https://github.com/pubnub/java/tree/v4.0.0)


  [Full Changelog](https://github.com/pubnub/java/compare/v4.0.0-beta4...v4.0.0)



- 🐛first GA.


## [v4.0.0-beta4](https://github.com/pubnub/java/tree/v4.0.0-beta4)


  [Full Changelog](https://github.com/pubnub/java/compare/v4.0.0-beta3...v4.0.0-beta4)


- ⭐reconnects and minor adjustments.



## [v4.0.0-beta3](https://github.com/pubnub/java/tree/v4.0.0-beta3)


  [Full Changelog](https://github.com/pubnub/java/compare/v4.0.0-beta2...v4.0.0-beta3)



- 🐛fixing state not coming on the subscriber callback.



- 🐛adjustments to URL encoding on publish, subscribe, set-state operations to avoid double encoding with retrofit.


## [v4.0.0-beta2](https://github.com/pubnub/java/tree/v4.0.0-beta2)


  [Full Changelog](https://github.com/pubnub/java/compare/v4.0.0-beta1...v4.0.0-beta2)


- ⭐reworking of message queue.



- ⭐checkstyle, findbugs.



- ⭐reworking error notifications.



## [v4.0.0-beta1](https://github.com/pubnub/java/tree/v4.0.0-beta1)




- ⭐initial beta1.

