# A2.py
# Author Brahma Dathan
# This program reads in the distance between two points (source and destination)
# and the distance traveled from source on the way to destination.
# It also reads in the minutes taken to travel from source so far.
# It then computes the remaining distance, the percentage of distance traveled,
# the average speed of travel, and the number of hours and minutes needed
# to travel the remaining distance at the current speed.
def main():
    print("Speed, distance, and time calculator")
    print()
    totalDistance, traveledDistance, timeTaken = \
                   eval(input("Enter the total distance, traveled distance, \
and time taken in minutes, separated by commas: "))
    percentageTraveled = traveledDistance * 100 / totalDistance
    hoursTraveled = timeTaken / 60
    speed = traveledDistance / hoursTraveled
    remainingDistance = totalDistance - traveledDistance
    timeNeeded = remainingDistance / speed
    hoursNeeded = int((timeNeeded * 10) // 10)
    minutesNeeded = int((timeNeeded - hoursNeeded) * 60)
    print("Percentage traveled", percentageTraveled, "%", "\nRemaining distance",\
          remainingDistance, "miles", "\nTraveling time so far", hoursTraveled,\
          "hours", "\nSpeed", speed, "mph", "\nTime needed", hoursNeeded, "hours", minutesNeeded, "minutes")
main()
    
    
