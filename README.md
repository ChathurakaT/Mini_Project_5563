Project Overview
This project is a simulation of the Quick Fit Algorithm for dynamic memory allocation. The algorithm is designed to allocate memory efficiently for frequent fixed-size allocation requests, minimizing fragmentation and improving speed. It was implemented as part of the EEX5563 course on Computer Architecture and Operating Systems at The Open University of Sri Lanka.

Features
Dynamic Memory Allocation: Handles allocation requests for predefined block sizes.
Fallback Mechanism: Uses a best-fit strategy when an exact block size is unavailable.
Fragmentation Management: Splits larger blocks to meet allocation needs and tracks remaining fragments.
Real-Time State Display: Outputs the status of free lists, allocated blocks, and fragments.

Code Structure
QuickFitAllocator Class:
Initializes free lists for predefined block sizes.
Allocates memory dynamically based on the Quick Fit algorithm.
Manages fragmentation through block splitting and free list updates.
Displays the current memory state.

Getting Started

Prerequisites
Java Development Kit (JDK) 11 or higher.
A Java IDE or text editor like IntelliJ IDEA, Eclipse, or Visual Studio Code.
