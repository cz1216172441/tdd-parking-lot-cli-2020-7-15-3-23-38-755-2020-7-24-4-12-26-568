### Story 2

---

**given**

A null number ticket

**when**

fetching car

**then**

"Unrecognized parking ticket."



---

**given**

A used ticket

**when**

fetching car

**then**

"Unrecognized parking ticket."



---

**given**

No ticket

**when**

fetching car

**then**

"Please provide your parking ticket."



---

**given**

A car, A full capacity parking lot

**when**

parking car

**then**

"Not enough position."