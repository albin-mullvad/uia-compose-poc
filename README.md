# UI Automator Compose PoC

This PoC aims to showcase a bug in Compose or UI Automator that result in flaky tests.

## App and test intro
The app is built using Compose and Compose Navigation and consists of two screens which can be
navigated between using a button. The test simply does the follow:
1. Open app and wait.
2. Ensure you're on the main screen.
3. Navigate to the secondary screen.
4. Ensure you're the secondary screen.
5. Navigate back to the main screen using the backstack.
6. Ensure you're on the main screen.

The check on step 6 will sometimes fail even though you're clearly on the main screen with the
expected element visible.

## How to easily trigger a test failure due to flakiness
Run the following script which will repeat the instrumented test sequence repeatedly, for example
100 times like so:
```bash
./run-tests-on-repeat.sh 100
```
